package com.example.springdataDemo.utilities;

import com.example.springdataDemo.model.Invoice;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Component
public class QRCodeGenerator {

    private static final String QR_CODE_IMAGE_PATH = "/Desktop/qr_codes/";

    public byte[] generateQRImage(Object name, Optional<Invoice> invoice) {

        //setting qr path
        String path = System.getProperty("user.home") + QR_CODE_IMAGE_PATH;

        //Creating qr matrix
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(
                    new String(invoice.toString().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8),
                    BarcodeFormat.QR_CODE, 200, 200);
        } catch (WriterException e) {
            throw new RuntimeException("Invoice data not converted to qr code sucessfully: " + e.getMessage());
        }

        //Creating directory for saving qr codes at client location
        try {
            Files.createDirectories(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException("Unable to create directory at server location: " + Path.of(path) +
                    "with message: " + e.getMessage());
        }

        //Creating image file path
        Path imagePath = Path.of(path + "qr_" + name + ".png");

        //writing the matrix to the path
        try {
            MatrixToImageWriter.writeToPath(matrix, "png", imagePath);
            invoice.orElseThrow().setQrPath(imagePath.toString());
        } catch (IOException e) {
            throw new RuntimeException("Unable to write image to path: " + imagePath + "with exception: "
                    + e.getMessage());
        }

        //writing the matrix to output stream as byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(matrix, "png", outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert the qr code to stream: " + e.getMessage());
        }

        //setting the byte array to invoice object
        invoice.orElseThrow().setQrCode(outputStream.toByteArray());

        return invoice.get().getQrCode();
    }

}
