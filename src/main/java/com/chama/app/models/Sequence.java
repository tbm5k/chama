package com.chama.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "se_sequence")
public class Sequence {

    @Id
    @Column(name = "se_id")
    private int sequenceId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "se_prefix")
    private String prefix;
    @Column(name = "se_number")
    private int number;
    @Column(name = "se_suffix")
    private int suffix;
    @Column(name = "ch_id_fk")
    private int chamaForeignKey;

    public Sequence() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSuffix() {
        return suffix;
    }

    public void setSuffix(int suffix) {
        this.suffix = suffix;
    }

    public int getChamaForeignKey() {
        return chamaForeignKey;
    }

    public void setChamaForeignKey(int chamaForeignKey) {
        this.chamaForeignKey = chamaForeignKey;
    }
}
