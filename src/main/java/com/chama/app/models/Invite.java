package com.chama.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "in_invite")
public class Invite {

    @Id
    @Column(name = "in_id")
    private int inviteId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "us_id_fk")
    private int userForeignKey;
    @Column(name = "ch_id_fk")
    private int chamaForeignKey;

    public Invite() {
        this.uuid = String.valueOf(UUID.randomUUID());
    }

    public int getInviteId() {
        return inviteId;
    }

    public void setInviteId(int inviteId) {
        this.inviteId = inviteId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getUserForeignKey() {
        return userForeignKey;
    }

    public void setUserForeignKey(int userForeignKey) {
        this.userForeignKey = userForeignKey;
    }

    public int getChamaForeignKey() {
        return chamaForeignKey;
    }

    public void setChamaForeignKey(int chamaForeignKey) {
        this.chamaForeignKey = chamaForeignKey;
    }
}
