package com.giriraaj.demo.controller;

import com.giriraaj.demo.model.QRInfo;
import com.giriraaj.demo.service.QRInfoService;
import com.giriraaj.demo.utilities.FileProcessingHelper;
import com.giriraaj.demo.utilities.QRCodeGenerator;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/qr")
@Slf4j
public class QRController {
    QRInfoService QRInfoService;
    QRCodeGenerator qrCodeGenerator;
    FileProcessingHelper fileProcessingHelper;

    @Autowired
    public QRController(QRInfoService QRInfoService, QRCodeGenerator qrCodeGenerator,
                        FileProcessingHelper fileProcessingHelper) {
        this.QRInfoService = QRInfoService;
        this.qrCodeGenerator = qrCodeGenerator;
        this.fileProcessingHelper = fileProcessingHelper;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<?> generateQR(@RequestParam("id") Long id) throws IOException, WriterException {
        Optional<QRInfo> invoice = QRInfoService.findById(id);

        byte[] imageBytes;
        try {
            imageBytes = qrCodeGenerator.generateQRImage(id, invoice);
        } catch (Exception e) {
            throw new RuntimeException("QR not generated successfully " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

    @GetMapping(value = "/getByInvoiceValue")
    public ResponseEntity<?> generateQRInvoiceValue(@RequestParam("invoiceValue") String invoiceValue) {

        QRInfo QRInfo = QRInfoService.findByInvoiceValue(invoiceValue);

        byte[] imageBytes;
        try {
            imageBytes = qrCodeGenerator.generateQRImage(invoiceValue, Optional.ofNullable(QRInfo));

        } catch (Exception e) {
            throw new RuntimeException("QR not generated successfully " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> generateAllQR() {

        List<QRInfo> QRInfos = QRInfoService.getAllInvoices();

        String qrLocation;
        try {
            qrLocation = qrCodeGenerator.generateQRImage(QRInfos);

        } catch (Exception e) {
            throw new RuntimeException("QR not generated successfully " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(qrLocation);
    }

    @GetMapping(value = "/download", produces = "application/zip")
    public void downloadQR(HttpServletRequest request,
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
    public ResponseEntity<?> downloadQRByFileName(HttpServletRequest request,
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
