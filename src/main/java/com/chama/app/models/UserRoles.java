package com.chama.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ur_user_roles")
public class UserRoles {

    @Id
    @GeneratedValue
    @Column(name = "ur_id")
    private int userRoleId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "ur_dashboard")
    private char dashboard;

    @ManyToOne
    @JoinColumn(name = "ro_id_fk")
    private Roles roleForeignKey;

    @ManyToOne
    @JoinColumn(name = "us_id_fk")
    private User userForeignKey;

    @OneToMany(mappedBy = "userRoles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserIntegrations> userIntegrations;

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
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

    public User getUserForeignKey() {
        return userForeignKey;
    }

    public void setUserForeignKey(User userForeignKey) {
        this.userForeignKey = userForeignKey;
    }

    public List<UserIntegrations> getUserIntegrations() {
        return userIntegrations;
    }

    public void setUserIntegrations(List<UserIntegrations> userIntegrations) {
        this.userIntegrations = userIntegrations;
    }
}
