package com.giriraaj.demo.controller;

import com.giriraaj.demo.model.Invoice;
import com.giriraaj.demo.model.QRInfo;
import com.giriraaj.demo.service.InvoiceDataService;
import com.giriraaj.demo.service.InvoiceService;
import com.giriraaj.demo.service.QRInfoService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoice")
@Slf4j
public class InvoiceController {
    InvoiceService invoiceService;
    QRInfoService qrInfoService;
    QRController qrController;

    InvoiceDataService invoiceDataService;


    @Autowired
    public InvoiceController(InvoiceService invoiceService, QRInfoService qrInfoService,
                             QRController qrController) {
        this.invoiceService = invoiceService;
        this.qrInfoService = qrInfoService;
        this.qrController = qrController;
    }

    @GetMapping(value = "/get/{inValue}")
    public ResponseEntity<?> getInvoice(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @PathVariable("invValue") String invValue) throws Exception {

        //Generate QR for particular invoice number
        QRInfo qrInfo = qrInfoService.findByInvoiceValue(invValue);
        qrController.generateQRInvoiceValue(invValue);

        //Get invoice header
        Invoice invoice = invoiceService.findByInvoiceValue(invValue);

        Map<String, Object> map = new HashMap<>();
        map.put("Image", System.getProperty("user.home") + File.separator + "/Desktop/qr_codes/qr_"
                + qrInfo.getInvValue() + ".png");
            map.put("invoiceNo", invoice.getInvValue());
            map.put("invoiceDate", invoice.getInvDate());
            map.put("sellerName", invoice.getSellerName());
            map.put("sellerAddress", invoice.getSellerAddress());
            map.put("sellerGstino", invoice.getSellerGstino());
            map.put("clientAddress", invoice.getClientAddress());
            map.put("telephoneNo", invoice.getClientTelNo());
            map.put("clientGstin", invoice.getClientGstino());
            map.put("transportName", invoice.getTransportName());
            map.put("LrNo", invoice.getLrNo());
            map.put("LrDate", invoice.getLrDate());
            map.put("noOfParcel", invoice.getNoOfParcel());
            map.put("partyOrderNo", invoice.getPartyOrderNo());
            map.put("lotNo", invoice.getLotNo());
            map.put("agentName", invoice.getAgentName());
            map.put("ewayBillNo", invoice.getEwayBillNo());

        //Get Invoice data
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(invoiceDataService
                .findByInvoiceValue(invValue));
        JasperReport compiledReport = JasperCompileManager.compileReport(
                new FileInputStream("src/main/resources/Invoice.jrxml"));

        //Fill report and export into pdf file
        String destFileName = System.getProperty("user.home") + File.separator +
                "/Desktop/invoice_qr_" + qrInfo.getInvValue() + ".pdf";
        JasperPrint report = JasperFillManager.fillReport(compiledReport, map, beanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(report, destFileName);

        //Stream report as byte
        File file = new File(destFileName);
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = inputStream.readAllBytes();

        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }
}
