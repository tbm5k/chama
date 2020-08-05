package com.chama.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Receipt {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "receipt_no")
    private int receiptNumber;
    @Column(name = "us_id_fk")
    private int userForeignKey;

    public Receipt() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public int getUserForeignKey() {
        return userForeignKey;
    }

    public void setUserForeignKey(int userForeignKey) {
        this.userForeignKey = userForeignKey;
    }
}
