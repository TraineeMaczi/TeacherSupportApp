package com.nokia.teachersupport.personSecurity;

import com.nokia.teachersupport.roles.SecutityRole;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class UserSecurityData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="DataId")
    private Integer id;

    @Version
    private Integer version;

    @Column(name = "activate")
    private Boolean active;

    private String password;

    private String matchingPassword;

    private String email;

    @ManyToMany(cascade=CascadeType.ALL , fetch=FetchType.EAGER)
    @JoinTable(
            name="securityUserDataAndSecurityRole",
            joinColumns=@JoinColumn( referencedColumnName="DataId"),
            inverseJoinColumns=@JoinColumn( referencedColumnName="RoleId"))
    private List<SecutityRole> myRoles=new ArrayList<>();

    public UserSecurityData(UserSecurityData userSecurityData) {
    this.id=userSecurityData.id;
    this.version=userSecurityData.version;
    this.active=userSecurityData.active;
    this.password=userSecurityData.password;
    this.matchingPassword=userSecurityData.matchingPassword;
    this.email=userSecurityData.email;
    this.myRoles=userSecurityData.myRoles;
    }


    public void addARole(SecutityRole secutityRole)
    {
        this.myRoles.add(secutityRole);
        if (!secutityRole.getSecurityInsAndRoles().contains(this)) {
            secutityRole.addUserSecurityDataToRole(this);
        }
    }

    public UserSecurityData() {
        this.active=false;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public List<SecutityRole> getMyRoles() {
        return myRoles;
    }

    public void setMyRoles(List<SecutityRole> myRoles) {
        this.myRoles = myRoles;
    }

    public boolean isActived() {
        return this.active == true;
    }

}
