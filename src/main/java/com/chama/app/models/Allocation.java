package com.chama.app.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "al_allocation")
public class Allocation {

    @Id
    @Column(name = "al_id")
    private int allocationId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "al_amount")
    private double amount;
    @Column(name = "al_receipt_date")
    private Date receiptDate;
    @Column(name = "al_receipt_number")
    private String receiptNumber;
    @Column(name = "al_period")
    private Date allocationPeriod;

    @ManyToOne
    @JoinColumn(name = "me_id_fk")
    private UserIntegrations userIntegrations;

    public int getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Date getAllocationPeriod() {
        return allocationPeriod;
    }

    public void setAllocationPeriod(Date allocationPeriod) {
        this.allocationPeriod = allocationPeriod;
    }

    public UserIntegrations getUserIntegrations() {
        return userIntegrations;
    }

    public void setUserIntegrations(UserIntegrations userIntegrations) {
        this.userIntegrations = userIntegrations;
    }
}
