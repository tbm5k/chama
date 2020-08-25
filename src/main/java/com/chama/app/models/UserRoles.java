package com.chama.app.models;

import javax.persistence.*;

@Entity
@Table(name = "ur_user_roles")
public class UserRoles {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int userRoleId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "ur_dashboard")
    private char dashboard;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Roles roleForeignKey;

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
}
