package com.giriraaj.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;

@Entity
@Table(name = "invoice_data")
public class InvoiceData {
    @Id
    private Long id;
    @Column(name = "hsn")
    private String hsn;
    @Column(name = "description")
    private String description;
    @Column(name = "design")
    private String design;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "rate")
    private String rate;
    @Column(name = "amount")
    private String amount;
    @Column(name = "inv_value")
    private String invValue;

    public InvoiceData() {
    }

    public InvoiceData(Long id, String hsn, String description, String design, String quantity, String rate,
                       String amount, String invValue) {
        this.id = id;
        this.hsn = hsn;
        this.description = description;
        this.design = design;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
        this.invValue = invValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInvValue() {
        return invValue;
    }

    public void setInvValue(String invValue) {
        this.invValue = invValue;
    }

    @Override
    public String toString() {
        return "InvoiceData{" +
                "id=" + id +
                ", hsn='" + hsn + '\'' +
                ", description='" + description + '\'' +
                ", design='" + design + '\'' +
                ", quantity='" + quantity + '\'' +
                ", rate='" + rate + '\'' +
                ", amount='" + amount + '\'' +
                ", invValue='" + invValue + '\'' +
                '}';
    }
}
