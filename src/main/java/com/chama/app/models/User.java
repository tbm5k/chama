package com.chama.app.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "us_users")
public class User {

    @Id
    @Column(name ="id")
    private int id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "us_fname")
    private String firstName;
    @Column(name = "us_lname")
    private String lastName;
    @Column(name = "us_username")
    private String username;
    @Column(name = "us_email")
    private String email;
    @Column(name = "password")
    private String password;
    @Transient
    private String confirmPassword;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<UserIntegrations> userIntegrations;

    public User(){
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<UserIntegrations> getUserIntegrations() {
        return userIntegrations;
    }

    public void setUserIntegrations(List<UserIntegrations> userIntegrations) {
        this.userIntegrations = userIntegrations;
    }
}
