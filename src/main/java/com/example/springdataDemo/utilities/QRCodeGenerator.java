package com.example.springdataDemo.utilities;

import com.example.springdataDemo.model.Invoice;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Component
public class QRCodeGenerator {
    public byte[] generateQRImage(Object name, Optional<Invoice> invoice, String path) throws WriterException, IOException, URISyntaxException {
        //Creating qr matrix
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(invoice.toString().getBytes("UTF-8"), "UTF-8"),
                BarcodeFormat.QR_CODE, 200, 200);

        //Creating directory for saving qr codes at client location
        Files.createDirectories(Path.of(path));

        //Creating image file path
        Path imagePath = Path.of(path + "qr_" + name + ".png");

        //writing the matrix to the path
        MatrixToImageWriter.writeToPath(matrix, "png", imagePath);

        //writing the matrix to output stream as byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "png", outputStream);

        //setting the byte array to invoice object
        invoice.orElseThrow().setQrCode(outputStream.toByteArray());

        return invoice.get().getQrCode();
    }

}
