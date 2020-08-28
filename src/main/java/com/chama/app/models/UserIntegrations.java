package com.chama.app.models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Ui_User_Integrations")
public class UserIntegrations {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "uuid")
    private String uuid;

    /*
    @Column(name = "ch_id_fk")
    private int chamaForeignKey;
    @Column(name = "us_id_fk")
    private int userForeignKey;
    @Column(name = "ur_id_fk")
    private Integer userRoleForeignKey;
    */

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Chama chama;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private UserRoles userRoles;

    public UserIntegrations() {
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
}
