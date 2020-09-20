package com.chama.app.models.multipledata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "ea_excel_allocation")
public class ExcelAllocation {

    @Id
    @Column(name = "ea_id")
    private int excelAllocationId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "ch_id_fk")
    private int chamaId;
    @Column(name = "me_id_fk")
    private int memberId;
    @Column(name = "ea_amount")
    private int allocationAmount;
    @Column(name = "ea_receipt_date")
    private Date allocationDate;
    @Column(name = "ea_receipt_number")
    private String receiptNumber;
    @Column(name = "ea_period")
    private Date allocationPeriod;

    public ExcelAllocation() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getExcelAllocationId() {
        return excelAllocationId;
    }

    public void setExcelAllocationId(int excelAllocationId) {
        this.excelAllocationId = excelAllocationId;
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

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getAllocationAmount() {
        return allocationAmount;
    }

    public void setAllocationAmount(int allocationAmount) {
        this.allocationAmount = allocationAmount;
    }

    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
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
}
