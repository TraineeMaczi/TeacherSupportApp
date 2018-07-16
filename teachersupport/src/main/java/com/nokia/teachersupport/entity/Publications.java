package com.nokia.teachersupport.entity;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@Entity
@Table(name = "publications")
public class Publications {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;
    private String publicationsInfoField;

    public Publications(){
        this.publicationsInfoField=Strings.EMPTY;
    }

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person publicationOwner;

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

    public String getPublicationsInfoField() {
        return publicationsInfoField;
    }

    public void setPublicationsInfoField(String publicationsInfoField) {
        this.publicationsInfoField = publicationsInfoField;
    }
    public void setPublicationOwner(Person publicationOwner) {
        this.publicationOwner = publicationOwner;
        if(!publicationOwner.getPersonPublicationsList().contains(this))
        {
            publicationOwner.getPersonPublicationsList().add(this);
        }
    }

    public Person getPublicationOwner() {
        return publicationOwner;
    }
}
