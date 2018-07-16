package com.nokia.teachersupport.entity;
import org.apache.logging.log4j.util.Strings;


import javax.persistence.*;

@Entity
@Table(name = "groupresources")
public class GroupResources {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Groups resourceOwner;


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

    public Groups getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(Groups resourceOwner) {
        this.resourceOwner = resourceOwner;
        if(!resourceOwner.getGroupsResourcesList().contains(this))
        {
            resourceOwner.getGroupsResourcesList().add(this);
        }
    }
}
