package com.giriraaj.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_data")
public class InvoiceData {
    @Id
    private Long id;
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "description")
    private String description;
    @Column(name = "design")
    private String design;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "inv_value")
    private String invValue;

    public InvoiceData() {
    }

    public InvoiceData(Long id, Integer itemId, String description, String design, Integer quantity, Double rate,
                       Double amount, String invValue) {
        this.id = id;
        this.itemId = itemId;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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
                ", itemId='" + itemId + '\'' +
                ", description='" + description + '\'' +
                ", design='" + design + '\'' +
                ", quantity='" + quantity + '\'' +
                ", rate='" + rate + '\'' +
                ", amount='" + amount + '\'' +
                ", invValue='" + invValue + '\'' +
                '}';
    }
}
