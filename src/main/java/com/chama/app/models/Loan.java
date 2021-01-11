package com.chama.app.models;

import javax.persistence.*;

@Entity
@Table
public class Loan {

    @Id
    private int loanId;
    @Column
    private int uuid;
    @Column
    private int amount;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "")
    private UserIntegrations memberId;
}
