package com.giriraaj.demo.controller;

import com.giriraaj.demo.model.QRInfo;
import com.giriraaj.demo.service.CSVService;
import com.giriraaj.demo.service.QRInfoService;
import com.giriraaj.demo.utilities.CSVHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qrInfo")
@Slf4j
public class QRInfoController {
    QRInfoService qrInfoService;

    @Autowired
    public QRInfoController(QRInfoService qrInfoService) {
        this.qrInfoService = qrInfoService;
    }


    @GetMapping(value = "/byIrn")
    public @ResponseBody List<QRInfo> getQRInfoByIrn(@RequestParam("irn") String irn) {
        return qrInfoService.findByIrn(irn);
    }

    @GetMapping(value = "/all")
    public @ResponseBody List<QRInfo> getAllQRInfo() {
        return qrInfoService.getAllInvoices();
    }

    @GetMapping(value = "/byInvoiceId")
    public @ResponseBody Optional<QRInfo> getQRInfoByInvoiceId(@RequestParam("id") Long id) {
        return qrInfoService.findById(id);
    }

    @PostMapping(value = "/uploadQRInfo")
    public ResponseEntity<?> uploadQRInfo(@RequestParam("file") MultipartFile file) throws Exception {
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                qrInfoService.saveQRInfo(file);
                message = "Congratulations!! File uploaded successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Invalid file format. Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
