package com.chama.app.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Table(name = "mc_member_contribution")
@Entity
public class MemberContribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mc_id")
    private int memberContributionId;
    @Column(name = "uuid")
    private String uuid;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ui_id_fk")
    private UserIntegrations member;

    @Column(name = "mc_amount")
    private int amount;
    @Column(name = "mc_month")
    private Date date;
    @Column(name = "mc_receipt_number")
    private String receiptNumber;
    @Column(name = "mc_receipt_date")
    private Date receiptDate;

    public MemberContribution() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getMemberContributionId() {
        return memberContributionId;
    }

    public void setMemberContributionId(int memberContributionId) {
        this.memberContributionId = memberContributionId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public UserIntegrations getMember() {
        return member;
    }

    public void setMember(UserIntegrations member) {
        this.member = member;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }
}
