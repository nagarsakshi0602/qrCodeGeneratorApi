package com.example.springdataDemo.utilities;

import com.example.springdataDemo.model.Invoice;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;

@Component
public class QRCodeGenerator {
    public Path generateQRImage(Object name, Optional<Invoice> invoice, String imagePath) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(invoice.toString().getBytes("UTF-8"), "UTF-8"),
                BarcodeFormat.QR_CODE, 200, 200);

        Path path = FileSystems.getDefault().getPath(imagePath + "_" + name + ".png");
        MatrixToImageWriter.writeToPath(matrix, "png", path);
        return path;
    }

}
