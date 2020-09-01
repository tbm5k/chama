package com.chama.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "re_receipt")
public class Receipt {

    @Id
    @Column(name = "re_id")
    private int receiptId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "us_id_fk")
    private Integer memberId;
    @Column(name = "re_number")
    private String receiptNumber;
    @Column(name = "re_amount")
    private int receiptAmount;
    @Column(name = "re_date")
    private Date receiptDate;
    @Column(name = "re_payment_mode")
    private String paymentMode;
    @Column(name = "re_payment_description")
    private String paymentDescription;
    @Column(name = "re_status")
    private String receiptStatus;
    @Column(name = "re_contribution_type")
    private String contributionType;
    @Column(name = "re_type")
    private String receiptType;

    public Receipt() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public int getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(int receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public String getContributionType() {
        return contributionType;
    }

    public void setContributionType(String contributionType) {
        this.contributionType = contributionType;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }
}
