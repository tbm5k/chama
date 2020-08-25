package com.chama.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ro_Roles")
public class Roles {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int roleId;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "ro_name")
    private String roleName;
    @Column(name = "ro_description")
    private String description;
    @Column(name = "ro_enabled")
    private char enabled;

    @OneToMany(mappedBy = "roleForeignKey")
    private List<UserRoles> userRolesList;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getEnabled() {
        return enabled;
    }

    public void setEnabled(char enabled) {
        this.enabled = enabled;
    }

    public List<UserRoles> getUserRolesList() {
        return userRolesList;
    }

    public void setUserRolesList(List<UserRoles> userRolesList) {
        this.userRolesList = userRolesList;
    }
}
