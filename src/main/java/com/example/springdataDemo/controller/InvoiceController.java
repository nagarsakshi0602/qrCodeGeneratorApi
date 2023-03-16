package com.example.springdataDemo.controller;

import com.example.springdataDemo.model.Invoice;
import com.example.springdataDemo.response.GetQRByInvoiceResponse;
import com.example.springdataDemo.service.InvoiceService;
import com.example.springdataDemo.utilities.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @Autowired
    QRCodeGenerator qrCodeGenerator;

    private static final String QR_CODE_IMAGE_PATH = "/Desktop/qr_codes/";


    @RequestMapping(value = "/invoice/{irn}", method = RequestMethod.GET)
    public @ResponseBody List<Invoice> getInvoiceByIrn(@PathVariable String irn) {
        return invoiceService.findByIrn(irn);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public @ResponseBody List<Invoice> getInvoice() {
        return invoiceService.getAllInvoices();
    }

    /*@RequestMapping(value = "/invoice/getQR/{id}", method = RequestMethod.GET)
    public ResponseEntity generateQR(@PathVariable Long id) throws IOException, WriterException {
        Optional<Invoice> invoice = invoiceService.findById(id);

        Path path = qrCodeGenerator.generateQRImage(id, invoice, QR_CODE_IMAGE_PATH);

        return ResponseEntity.status(HttpStatus.OK).body(path);
    }*/

    @RequestMapping(value = "/invoice/getQRByInvoiceValue/{invoiceValue}", method = RequestMethod.GET)
    public ResponseEntity<?> generateQRInvoiceValue(@PathVariable String invoiceValue)
            throws IOException, WriterException, URISyntaxException {

        Invoice invoice = invoiceService.findByInvoiceValue(invoiceValue);

        //setting qr path
        String qr_path = System.getProperty("user.home") + QR_CODE_IMAGE_PATH;

        byte[] imageBytes = qrCodeGenerator.generateQRImage(invoiceValue, Optional.ofNullable(invoice), qr_path);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }
}
