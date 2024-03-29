package com.giriraaj.demo.view_controller;

import com.giriraaj.demo.controller.QRController;
import com.giriraaj.demo.utilities.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GenerateQRViewController {
    private QRController qrController;

    @Autowired
    public GenerateQRViewController(QRController qrController) {
        this.qrController = qrController;
    }

    @GetMapping("/generateQR")
    public String uploadStatus() {
        return "generateQR";
    }

    @GetMapping("/generateAllQR")
    public Object generateAllQR(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                RedirectAttributes redirectAttributes) {
        ResponseEntity<?> response = null;
        try {
            response = qrController.generateAllQR();
            redirectAttributes.addFlashAttribute("message",
                    "QR code generated successfully " + response.getBody().toString());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "QR code not generated successfully: " + e.getMessage());
        }
        try {
            qrController.downloadQR(httpServletRequest, httpServletResponse);
            redirectAttributes.addFlashAttribute("message",
                    "Zip downloaded successfully");
            return response;
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
            response = qrController.generateQRInvoiceValue(invValue);
            redirectAttributes.addFlashAttribute("message", "Saved image location: "
                    + QRCodeGenerator.getPath());

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "QR code not generated successfully: " + e.getMessage());
        }
        try {
            qrController.downloadQRByFileName(httpServletRequest, httpServletResponse, invValue);
            return response;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Unable to download zip file: " + e.getMessage());
        }
        return "redirect:/generateQR";
    }

}
