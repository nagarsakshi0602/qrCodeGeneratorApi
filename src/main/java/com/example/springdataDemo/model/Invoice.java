package com.example.springdataDemo.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="irn")
    private String irn;
    @Column(name="ack_no")
    private String ackNo;
    @Column(name="ack_date")
    private String ackDate;
    @Column(name="doc_no")
    private String docNo;
    @Column(name="doc_type")
    private String docType;
    @Column(name="doc_date")
    private String docDate;
    @Column(name="inv_value")
    private String invValue;
    @Column(name="recipient_gstin")
    private String recipientGSTIN;
    @Column(name="status")
    private String status;
    @Column(name="signature")
    private String signature;
    @Column(name="ewb_no")
    private String ewbNo;
    @Column(name="qr_code")
    private byte[] qrCode;

    @Column(name="qr_path")
    private String qrPath;

    public Invoice() {
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
                ", qrCode=" + Arrays.toString(qrCode) +
                ", qrPath='" + qrPath + '\'' +
                '}';
    }
}
