package com.chama.app.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Ui_User_Integrations")
public class UserIntegrations {

    @Id
    @Column(name = "ui_id")
    private int userIntegrationsId;
    @Column(name = "uuid")
    private String uuid;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ch_id_fk")
    private Chama chama;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "us_id_fk")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ur_id_fk")
    private UserRoles userRoles;

    @OneToMany(mappedBy = "memberId")
    private List<Allocation> allocations;

    @OneToMany(mappedBy = "member")
    private List<Loan> loans;

    @OneToMany(mappedBy = "member")
    private List<Receipt> receipts;

    @OneToMany(mappedBy = "member")
    private List<MemberContribution> contributions;

    @OneToMany(mappedBy = "guarantor")
    private List<Guarantor> grantList;

    public UserIntegrations() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getUserIntegrationsId() {
        return userIntegrationsId;
    }

    public void setUserIntegrationsId(int userIntegrationsId) {
        this.userIntegrationsId = userIntegrationsId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Chama getChama() {
        return chama;
    }

    public void setChama(Chama chama) {
        this.chama = chama;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public List<MemberContribution> getContributions() {
        return contributions;
    }

    public void setContributions(List<MemberContribution> contributions) {
        this.contributions = contributions;
    }
}
