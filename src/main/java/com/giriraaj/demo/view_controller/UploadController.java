package com.giriraaj.demo.view_controller;

import com.giriraaj.demo.controller.InvoiceController;
import com.giriraaj.demo.utilities.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UploadController {

    private InvoiceController invoiceController;

    @Autowired
    public UploadController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestPart("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/";
        }
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                ResponseEntity response = invoiceController.uploadInvoice(file);
                redirectAttributes.addFlashAttribute("message",
                        response.getBody().toString());

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "File Upload Failed!");
                return "redirect:/";
            }
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "Invalid file format. Please upload a csv file!");
            return "redirect:/";
        }

        return "redirect:/generateQR";
    }

}
