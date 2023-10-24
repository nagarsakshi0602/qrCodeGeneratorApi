package com.giriraaj.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;

@Entity
@Table(name = "invoice_header")
public class Invoice {
    @Id
    private Long id;
    @Column(name = "inv_value")
    private String invValue;
    @Column(name = "inv_date")
    private String invDate;
    @Column(name = "seller_name")
    private String sellerName;
    @Column(name = "seller_address")
    private String sellerAddress;
    @Column(name = "seller_gstino")
    private String sellerGstino;
    @Column(name = "client_address")
    private String clientAddress;
    @Column(name = "client_tel_no")
    private String clientTelNo;
    @Column(name = "client_gstino")
    private String clientGstino;
    @Column(name = "transport_name")
    private String transportName;
    @Column(name = "lr_no")
    private String lrNo;
    @Column(name = "lr_date")
    private String lrDate;
    @Column(name = "no_of_parcel")
    private byte[] noOfParcel;

    @Column(name = "party_order_no")
    private String partyOrderNo;

    @Column(name = "lot_no")
    private String lotNo;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "eway_bill_no")
    private String ewayBillNo;

    public Invoice() {
    }

    public Invoice(Long id, String invValue, String invDate, String sellerName, String sellerAddress,
                   String sellerGstino, String clientAddress, String clientTelNo, String clientGstino,
                   String transportName, String lrNo, String lrDate, byte[] noOfParcel, String partyOrderNo,
                   String lotNo, String agentName, String ewayBillNo) {
        this.id = id;
        this.invValue = invValue;
        this.invDate = invDate;
        this.sellerName = sellerName;
        this.sellerAddress = sellerAddress;
        this.sellerGstino = sellerGstino;
        this.clientAddress = clientAddress;
        this.clientTelNo = clientTelNo;
        this.clientGstino = clientGstino;
        this.transportName = transportName;
        this.lrNo = lrNo;
        this.lrDate = lrDate;
        this.noOfParcel = noOfParcel;
        this.partyOrderNo = partyOrderNo;
        this.lotNo = lotNo;
        this.agentName = agentName;
        this.ewayBillNo = ewayBillNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvValue() {
        return invValue;
    }

    public void setInvValue(String invValue) {
        this.invValue = invValue;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerGstino() {
        return sellerGstino;
    }

    public void setSellerGstino(String sellerGstino) {
        this.sellerGstino = sellerGstino;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientTelNo() {
        return clientTelNo;
    }

    public void setClientTelNo(String clientTelNo) {
        this.clientTelNo = clientTelNo;
    }

    public String getClientGstino() {
        return clientGstino;
    }

    public void setClientGstino(String clientGstino) {
        this.clientGstino = clientGstino;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getLrNo() {
        return lrNo;
    }

    public void setLrNo(String lrNo) {
        this.lrNo = lrNo;
    }

    public String getLrDate() {
        return lrDate;
    }

    public void setLrDate(String lrDate) {
        this.lrDate = lrDate;
    }

    public byte[] getNoOfParcel() {
        return noOfParcel;
    }

    public void setNoOfParcel(byte[] noOfParcel) {
        this.noOfParcel = noOfParcel;
    }

    public String getPartyOrderNo() {
        return partyOrderNo;
    }

    public void setPartyOrderNo(String partyOrderNo) {
        this.partyOrderNo = partyOrderNo;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getEwayBillNo() {
        return ewayBillNo;
    }

    public void setEwayBillNo(String ewayBillNo) {
        this.ewayBillNo = ewayBillNo;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invValue='" + invValue + '\'' +
                ", invDate='" + invDate + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", sellerGstino='" + sellerGstino + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientTelNo='" + clientTelNo + '\'' +
                ", clientGstino='" + clientGstino + '\'' +
                ", transportName='" + transportName + '\'' +
                ", lrNo='" + lrNo + '\'' +
                ", lrDate='" + lrDate + '\'' +
                ", noOfParcel=" + Arrays.toString(noOfParcel) +
                ", partyOrderNo='" + partyOrderNo + '\'' +
                ", lotNo='" + lotNo + '\'' +
                ", agentName='" + agentName + '\'' +
                ", ewayBillNo='" + ewayBillNo + '\'' +
                '}';
    }
}
