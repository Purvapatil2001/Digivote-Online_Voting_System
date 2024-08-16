package com.app.entities;

import java.util.Random;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ee_data")
public class Executive {

    @Id
    @Column(name = "eid", nullable = false, unique = true)
    private String eid;

    private String ename;

    @Column(name = "password", nullable = false)
    private String password;

    private String estate;

    @Enumerated(EnumType.STRING)
    private AdminRoles roles;

    // Constructor without 'eid' since it is generated manually
    public Executive(String ename, String estate, AdminRoles roles, String password) {
        super();
        Random random = new Random();
        this.eid = "VID" + ename.charAt(0) + estate.charAt(0)
                + String.format("%05d", random.nextInt(98999) + 1000);
        this.ename = ename;
        this.estate = estate;
        this.roles = roles;
        this.password = password;
    }

    // Default constructor
    public Executive() {
        super();
    }

    // Getters and Setters
    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminRoles getRoles() {
        return roles;
    }

    public void setRoles(AdminRoles roles) {
        this.roles = roles;
    }
}
