package com.nokia.teachersupport.roles;

import com.nokia.teachersupport.personSecurity.UserSecurityData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SecutityRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RoleId")
    private Integer id;

    @Version
    private Integer version;

    private String roleName;

    @ManyToMany(mappedBy="myRoles", cascade = { CascadeType.ALL },fetch=FetchType.EAGER)
    private List<UserSecurityData> securityInsAndRoles=new ArrayList<>();

    public SecutityRole()
    {
        roleName="ROLE_USER";

    }
    public void addUserSecurityDataToRole(UserSecurityData userSecurityData)
    {
        this.securityInsAndRoles.add(userSecurityData);
        //!UWAGA
        if (!userSecurityData.getMyRoles().contains(this)) {
            userSecurityData.addARole(this);
        }

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserSecurityData> getSecurityInsAndRoles() {
        return securityInsAndRoles;
    }

    public void setSecurityInsAndRoles(List<UserSecurityData> securityInsAndRoles) {
        this.securityInsAndRoles = securityInsAndRoles;
    }
}
