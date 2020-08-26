package com.chama.app.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Ch_Chama")
public class Chama {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "ch_name")
    private String name;

    @OneToMany(mappedBy = "chama", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserIntegrations> userIntegrations;

    public Chama() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserIntegrations> getUserIntegrations() {
        return userIntegrations;
    }

    public void setUserIntegrations(List<UserIntegrations> userIntegrations) {
        this.userIntegrations = userIntegrations;
    }
}
