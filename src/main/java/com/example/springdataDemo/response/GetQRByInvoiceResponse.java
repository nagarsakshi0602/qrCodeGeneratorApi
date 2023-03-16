package com.example.springdataDemo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class GetQRByInvoiceResponse {
    @JsonProperty("invoice_value")
    private String invoiceValue;
    @JsonProperty("qr_path")
    private String qrPath;

    public GetQRByInvoiceResponse(String invoiceValue, String qrPath) {
        this.invoiceValue = invoiceValue;
        this.qrPath = qrPath;
    }

    public String getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(String invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getQrPath() {
        return qrPath;
    }

    public void setQrPath(String qrPath) {
        this.qrPath = qrPath;
    }

    @Override
    public String toString() {
        return "getQRByInvoiceResponse{" +
                "invoiceValue='" + invoiceValue + '\'' +
                ", qrPath='" + qrPath + '\'' +
                '}';
    }
}
