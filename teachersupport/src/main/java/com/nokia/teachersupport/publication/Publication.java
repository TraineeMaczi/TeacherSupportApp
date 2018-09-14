package com.nokia.teachersupport.publication;

import com.nokia.teachersupport.person.Person;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String publicationInfoField;

    public Publication() {
        this.publicationInfoField = Strings.EMPTY;
    }

    @ManyToOne
    @JoinColumn(name = "person_id")
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

    public String getPublicationInfoField() {
        return publicationInfoField;
    }

    public void setPublicationInfoField(String publicationInfoField) {
        this.publicationInfoField = publicationInfoField;
    }

    public void setPublicationOwner(Person publicationOwner) {
        this.publicationOwner = publicationOwner;
        if (!publicationOwner.getPersonPublicationList().contains(this)) {
            publicationOwner.getPersonPublicationList().add(this);
        }
    }

    public Person getPublicationOwner() {
        return publicationOwner;
    }
}
