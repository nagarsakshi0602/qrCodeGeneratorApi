package com.example.springdataDemo.controller;

import com.example.springdataDemo.model.Invoice;
import com.example.springdataDemo.service.InvoiceService;
import com.example.springdataDemo.utilities.QRCodeGenerator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @Autowired
    QRCodeGenerator qrCodeGenerator;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/qr_codes/QRCode";


    @RequestMapping(value = "/invoice/{irn}", method = RequestMethod.GET)
    public @ResponseBody List<Invoice> getInvoiceByIrn(@PathVariable String irn) {
        return invoiceService.findByIrn(irn);
    }

    @RequestMapping(value = "/invoice/getQR/{id}", method = RequestMethod.GET)
    public ResponseEntity generateQR(@PathVariable Long id) throws IOException, WriterException {
        Optional<Invoice> invoice = invoiceService.findById(id);

        Path path = qrCodeGenerator.generateQRImage(id, invoice, QR_CODE_IMAGE_PATH);

        return ResponseEntity.status(HttpStatus.OK).body(path);
    }

    @RequestMapping(value = "/invoice/getQRByInvoiceValue/{invoiceValue}", method = RequestMethod.GET)
    public ResponseEntity<Path> generateQRInvoiceValue(@PathVariable String invoiceValue)
            throws IOException, WriterException {
        Invoice invoice = invoiceService.findByInvoiceValue(invoiceValue);

        Path path = qrCodeGenerator.generateQRImage(invoiceValue, Optional.ofNullable(invoice), QR_CODE_IMAGE_PATH);

        return ResponseEntity.status(HttpStatus.OK).body(path);
    }
}
