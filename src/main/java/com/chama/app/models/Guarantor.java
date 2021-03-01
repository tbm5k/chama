package com.chama.app.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "gu_guarantor")
public class Guarantor {

    @Id
    @Column(name = "gu_id")
    private int guarantId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "gu_amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "lo_id_fk")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "gu_guarantor_id")
    private UserIntegrations guarantor;


    public Guarantor() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getGuarantId() {
        return guarantId;
    }

    public void setGuarantId(int guarantId) {
        this.guarantId = guarantId;
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

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public UserIntegrations getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(UserIntegrations guarantor) {
        this.guarantor = guarantor;
    }
}
