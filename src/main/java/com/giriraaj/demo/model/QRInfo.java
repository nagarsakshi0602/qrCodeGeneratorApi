package com.giriraaj.demo.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qr_info")
public class QRInfo {
    @Id
    @CsvBindByName(column = "Sl. No")
    private Long id;
    @Column(name = "irn")
    @CsvBindByName(column = "IRN")
    private String irn;
    @Column(name = "ack_no")
    @CsvBindByName(column = "Ack No")
    private String ackNo;
    @Column(name = "ack_date")
    @CsvBindByName(column = "Ack Date")
    private String ackDate;
    @Column(name = "doc_no")
    @CsvBindByName(column = "Doc No")
    private String docNo;
    @Column(name = "doc_type")
    @CsvBindByName(column = "Doc Typ")
    private String docType;
    @Column(name = "doc_date")
    @CsvBindByName(column = "Doc Date")
    private String docDate;
    @Column(name = "inv_value")
    @CsvBindByName(column = "Inv Value.")
    private String invValue;
    @Column(name = "recipient_gstin")
    @CsvBindByName(column = "Recipient GSTIN")
    private String recipientGSTIN;
    @Column(name = "status")
    @CsvBindByName(column = "Status")
    private String status;
    @Column(name = "signature")
    @CsvBindByName(column = "Signed QR Code")
    private String signature;
    @Column(name = "ewb_no")
    @CsvBindByName(column = "EWB No./ If Any Errors While Creating EWB.")
    private String ewbNo;
    @Column(name = "qr_code")
    private byte[] qrCode;

    @Column(name = "qr_path")
    private String qrPath;

    public QRInfo() {
    }

    public QRInfo(Long id, String irn, String ackNo, String ackDate, String docNo, String docType, String docDate, String invValue, String recipientGSTIN, String status, String signature, String ewbNo, byte[] qrCode, String qrPath) {
        this.id = id;
        this.irn = irn;
        this.ackNo = ackNo;
        this.ackDate = ackDate;
        this.docNo = docNo;
        this.docType = docType;
        this.docDate = docDate;
        this.invValue = invValue;
        this.recipientGSTIN = recipientGSTIN;
        this.status = status;
        this.signature = signature;
        this.ewbNo = ewbNo;
        this.qrCode = qrCode;
        this.qrPath = qrPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIrn() {
        return irn;
    }

    public void setIrn(String irn) {
        this.irn = irn;
    }

    public String getAckNo() {
        return ackNo;
    }

    public void setAckNo(String ackNo) {
        this.ackNo = ackNo;
    }

    public String getAckDate() {
        return ackDate;
    }

    public void setAckDate(String ackDate) {
        this.ackDate = ackDate;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getInvValue() {
        return invValue;
    }

    public void setInvValue(String invValue) {
        this.invValue = invValue;
    }

    public String getRecipientGSTIN() {
        return recipientGSTIN;
    }

    public void setRecipientGSTIN(String recipientGSTIN) {
        this.recipientGSTIN = recipientGSTIN;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEwbNo() {
        return ewbNo;
    }

    public void setEwbNo(String ewbNo) {
        this.ewbNo = ewbNo;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrPath() {
        return qrPath;
    }

    public void setQrPath(String qrPath) {
        this.qrPath = qrPath;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", irn='" + irn + '\'' +
                ", ackNo='" + ackNo + '\'' +
                ", ackDate='" + ackDate + '\'' +
                ", docNo='" + docNo + '\'' +
                ", docType='" + docType + '\'' +
                ", docDate='" + docDate + '\'' +
                ", invValue='" + invValue + '\'' +
                ", recipientGSTIN='" + recipientGSTIN + '\'' +
                ", status='" + status + '\'' +
                ", signature='" + signature + '\'' +
                ", ewbNo='" + ewbNo + '\'' +
                "}";
    }
}
