package com.nokia.teachersupport.personSecurity;

import com.nokia.teachersupport.roles.SecutityRole;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.descriptor.web.SecurityRoleRef;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

@PasswordMatches
@Entity
public class UserSecurityData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="DataId")
    private Integer id;

    @Version
    private Integer version;

    private Boolean active;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name="securityUserDataAndSecurityRole",
            joinColumns=@JoinColumn( referencedColumnName="DataId"),
            inverseJoinColumns=@JoinColumn( referencedColumnName="RoleId"))
    private List<SecutityRole> myRoles;

    public void addARole(String rName)
    {
        SecutityRole newRole=new SecutityRole();
        newRole.setRoleName(rName);
        this.myRoles.add(newRole);
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
}
