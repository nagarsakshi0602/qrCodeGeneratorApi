package com.example.springdataDemo.controller;

import com.example.springdataDemo.model.Invoice;
import com.example.springdataDemo.service.CSVService;
import com.example.springdataDemo.service.InvoiceService;
import com.example.springdataDemo.utilities.CSVHelper;
import com.example.springdataDemo.utilities.FileProcessingHelper;
import com.example.springdataDemo.utilities.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @Autowired
    CSVService csvService;
    @Autowired
    QRCodeGenerator qrCodeGenerator;

    @Autowired
    FileProcessingHelper fileProcessingHelper;


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

    @GetMapping(value = "/download", produces = "application/zip")
    public void download(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename=qr_codes.zip");

        List<String> files = fileProcessingHelper.listAllFilesFromGivenPath(System.getProperty("user.home")
                + QRCodeGenerator.QR_CODE_IMAGE_PATH);

        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        /* Gather all files and put it in a ZIP */
        for (String file : files) {
            fileProcessingHelper.addGivenFileToZip(zipOut, file);
        }
        zipOut.finish();
        zipOut.close();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @GetMapping(value = "/download/{invValue}")
    public ResponseEntity<?> downloadByFileName(HttpServletRequest request,
                                                HttpServletResponse response,
                                                @PathVariable("invValue") String invValue) throws Exception {

        response.setContentType(String.valueOf(MediaType.IMAGE_PNG));
        response.setHeader("Content-Disposition", "attachment;filename=qr_" + invValue + ".png");


        File file = new File(System.getProperty("user.home")
                + QRCodeGenerator.QR_CODE_IMAGE_PATH + "qr_" + invValue + ".png");

        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = inputStream.readAllBytes();


        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }
}
