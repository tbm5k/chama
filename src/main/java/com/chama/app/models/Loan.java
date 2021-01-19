package com.chama.app.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "lo_loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lo_id")
    private int loanId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "lo_amount")
    private int amount;
    @Column(name = "lo_period")
    private Date loanPeriod;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ui_id_fk")
    private UserIntegrations member;

    public Loan() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Date loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public UserIntegrations getMember() {
        return member;
    }

    public void setMember(UserIntegrations member) {
        this.member = member;
    }
}
