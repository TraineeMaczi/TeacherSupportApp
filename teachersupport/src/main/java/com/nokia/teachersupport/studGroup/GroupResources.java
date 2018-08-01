package com.nokia.teachersupport.studGroup;

import javax.persistence.*;

@Entity
public class GroupResources {
    //TODO SUPERCLASS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

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
}
