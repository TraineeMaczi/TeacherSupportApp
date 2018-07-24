package com.nokia.teachersupport.groupsSchool;

import com.nokia.teachersupport.groupsSchool.GroupsSchool;

import javax.persistence.*;

@Entity
public class GroupResources {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupsSchool resourceOwner;


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

    public GroupsSchool getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(GroupsSchool resourceOwner) {
        this.resourceOwner = resourceOwner;
        if (!resourceOwner.getGroupsResourcesList().contains(this)) {
            resourceOwner.getGroupsResourcesList().add(this);
        }
    }
}
