package com.example.springdataDemo.view_controller;

import com.example.springdataDemo.controller.InvoiceController;
import com.example.springdataDemo.utilities.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UploadController {

    @Autowired
    InvoiceController invoiceController;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/";
        }

        try {
            invoiceController.uploadInvoice(file);
            redirectAttributes.addFlashAttribute("message",
                    "Congratulations!! You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/generateQR";
    }

    @GetMapping("/generateQR")
    public String uploadStatus() {
        return "generateQR";
    }

    @GetMapping("/generateAllQR")
    public Object generateAllQR(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                RedirectAttributes redirectAttributes) {

        try {
            ResponseEntity<?> response = invoiceController.generateAllQR();
            redirectAttributes.addFlashAttribute("message",
                    "QR code generated successfully " + response.getBody().toString());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "QR code not generated successfully: " + e.getMessage());
        }
        try {
            invoiceController.download(httpServletRequest, httpServletResponse);
            redirectAttributes.addFlashAttribute("message",
                    "Zip downloaded successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Unable to download zip file: " + e.getMessage());
        }
        return "redirect:/generateQR";
    }

    @PostMapping("/generateQRByInvoice")
    public Object generateQRByInvoice(@RequestParam(value = "invValue") String invValue,
                                      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                      RedirectAttributes redirectAttributes) {
        ResponseEntity<?> response = null;
        try {
            response = invoiceController.generateQRInvoiceValue(invValue);
            redirectAttributes.addFlashAttribute("message", "Saved image location: "
                    + QRCodeGenerator.getPath());

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "QR code not generated successfully: " + e.getMessage());
        }
        try {
            invoiceController.downloadByFileName(httpServletRequest, httpServletResponse, invValue);
            return response;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Unable to download zip file: " + e.getMessage());
        }
        return "redirect:/generateQR";
    }
}
