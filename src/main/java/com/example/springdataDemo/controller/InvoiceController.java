package com.example.springdataDemo.controller;

import com.example.springdataDemo.model.Invoice;
import com.example.springdataDemo.service.CSVService;
import com.example.springdataDemo.service.InvoiceService;
import com.example.springdataDemo.utilities.CSVHelper;
import com.example.springdataDemo.utilities.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @Autowired
    CSVService csvService;
    @Autowired
    QRCodeGenerator qrCodeGenerator;

    @GetMapping(value = "/invoiceByIrn")
    public @ResponseBody List<Invoice> getInvoiceByIrn(@RequestParam("irn") String irn) {
        return invoiceService.findByIrn(irn);
    }

    @GetMapping(value = "/all")
    public @ResponseBody List<Invoice> getInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping(value = "/invoiceById")
    public @ResponseBody Optional<Invoice> getInvoice(@RequestParam("id") Long id) {
        return invoiceService.findById(id);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadInvoice(@RequestParam("file") MultipartFile file) throws Exception {
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                csvService.saveInvoice(file);
                message = "File uploaded successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping(value = "/getQRById")
    public ResponseEntity<?> generateQR(@RequestParam("id") Long id) throws IOException, WriterException {
        Optional<Invoice> invoice = invoiceService.findById(id);

        byte[] imageBytes;
        try {
            imageBytes = qrCodeGenerator.generateQRImage(id, invoice);
        } catch (Exception e) {
            throw new RuntimeException("QR not generated successfully " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

    @GetMapping(value = "/getQRByInvoiceValue")
    public ResponseEntity<?> generateQRInvoiceValue(@RequestParam("invoiceValue") String invoiceValue) {

        Invoice invoice = invoiceService.findByInvoiceValue(invoiceValue);

        byte[] imageBytes;
        try {
            imageBytes = qrCodeGenerator.generateQRImage(invoiceValue, Optional.ofNullable(invoice));

        } catch (Exception e) {
            throw new RuntimeException("QR not generated successfully " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

    @GetMapping(value = "/getAllQR")
    public ResponseEntity<?> generateAllQR() {

        List<Invoice> invoices = invoiceService.getAllInvoices();

        String qrLocation;
        try {
            qrLocation = qrCodeGenerator.generateQRImage(invoices);

        } catch (Exception e) {
            throw new RuntimeException("QR not generated successfully " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(qrLocation);
    }
}
