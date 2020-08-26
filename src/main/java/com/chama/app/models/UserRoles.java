package com.chama.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ur_user_roles")
public class UserRoles {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "ur_dashboard")
    private char dashboard;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Roles roleForeignKey;

    @OneToMany(mappedBy = "userRoles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserIntegrations> userIntegrations;

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

    public char getDashboard() {
        return dashboard;
    }

    public void setDashboard(char dashboard) {
        this.dashboard = dashboard;
    }

    public Roles getRoleForeignKey() {
        return roleForeignKey;
    }

    public void setRoleForeignKey(Roles roleForeignKey) {
        this.roleForeignKey = roleForeignKey;
    }

    public List<UserIntegrations> getUserIntegrations() {
        return userIntegrations;
    }

    public void setUserIntegrations(List<UserIntegrations> userIntegrations) {
        this.userIntegrations = userIntegrations;
    }
}
