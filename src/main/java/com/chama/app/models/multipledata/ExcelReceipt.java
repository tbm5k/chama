package com.chama.app.models.multipledata;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "er_excel_receipt")
public class ExcelReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "er_id")
    private int excelReceiptId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "ch_id_fk")
    private int chamaId;
    @Column(name = "us_id_fk")
    private int userId;
    @Column(name = "er_number")
    private String receiptNumber;
    @Column(name = "er_amount")
    private int receiptAmount;
    @Column(name = "er_date")
    private Date receiptDate;
    @Column(name = "er_payment_mode")
    private String paymentMode;
    @Column(name = "er_payment_description")
    private String paymentDescription;
    @Column(name = "er_status")
    private String receiptStatus;
    @Column(name = "er_contribution_type")
    private String contributionType;
    @Column(name = "er_type")
    private String receiptType;

    public ExcelReceipt() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getExcelReceiptId() {
        return excelReceiptId;
    }

    public void setExcelReceiptId(int excelReceiptId) {
        this.excelReceiptId = excelReceiptId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getChamaId() {
        return chamaId;
    }

    public void setChamaId(int chamaId) {
        this.chamaId = chamaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
