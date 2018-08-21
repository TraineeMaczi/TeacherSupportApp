package com.nokia.teachersupport.studGroup;

import javax.persistence.*;

@Entity
public class GroupRemoteResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String studGroupResourceName;
    private String resourceLink;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private StudGroup resourceOwner;


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

    public StudGroup getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(StudGroup resourceOwner) {
        this.resourceOwner = resourceOwner;
        if (!resourceOwner.getGroupsResourcesList().contains(this)) {
            resourceOwner.getGroupsResourcesList().add(this);
        }
    }

    public String getResourceLink() {
        return resourceLink;
    }

    public void setResourceLink(String resourceLink) {
        this.resourceLink = resourceLink;
    }

    public String getStudGroupResourceName() {
        return studGroupResourceName;
    }

    public void setStudGroupResourceName(String studGroupResourceName) {
        this.studGroupResourceName = studGroupResourceName;
    }
}
