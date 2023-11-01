package com.giriraaj.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Arrays;

@Entity
@Table(name = "invoice_header")
public class Invoice {
    @Id
    private Long id;
    @Column(name = "inv_value")
    private String invValue;
    @Column(name = "inv_date")
    private Date invDate;
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
    private Date lrDate;
    @Column(name = "no_of_parcel")
    private Integer noOfParcel;
    @Column(name = "party_order_no")
    private Integer partyOrderNo;
    @Column(name = "lot_no")
    private Integer lotNo;
    @Column(name = "agent_name")
    private String agentName;
    @Column(name = "eway_bill_no")
    private String ewayBillNo;

    public Invoice() {
    }

    public Invoice(Long id, String invValue, Date invDate, String sellerName, String sellerAddress,
                   String sellerGstino, String clientAddress, String clientTelNo, String clientGstino,
                   String transportName, String lrNo, Date lrDate, Integer noOfParcel, Integer partyOrderNo,
                   Integer lotNo, String agentName, String ewayBillNo) {
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

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
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

    public Date getLrDate() {
        return lrDate;
    }

    public void setLrDate(Date lrDate) {
        this.lrDate = lrDate;
    }

    public Integer getNoOfParcel() {
        return noOfParcel;
    }

    public void setNoOfParcel(Integer noOfParcel) {
        this.noOfParcel = noOfParcel;
    }

    public Integer getPartyOrderNo() {
        return partyOrderNo;
    }

    public void setPartyOrderNo(Integer partyOrderNo) {
        this.partyOrderNo = partyOrderNo;
    }

    public Integer getLotNo() {
        return lotNo;
    }

    public void setLotNo(Integer lotNo) {
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
                ", noOfParcel=" + noOfParcel +
                ", partyOrderNo='" + partyOrderNo + '\'' +
                ", lotNo='" + lotNo + '\'' +
                ", agentName='" + agentName + '\'' +
                ", ewayBillNo='" + ewayBillNo + '\'' +
                '}';
    }
}
