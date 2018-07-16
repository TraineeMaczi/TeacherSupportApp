package com.nokia.teachersupport.entity;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String degreeField;
    private String nameField;
    private String surnameField;
    private String facultyPictureField;
    private String workAddresField;
    private String professionField;
    private String phoneNumberField;
    private String usosPersonProfileLinkField;
    private String hobbyField;

    //private String fotoField;
    //private String cvField;


    @OneToMany(mappedBy="newsOwner")
    private List<News> personNewsList;

    @OneToMany(mappedBy="publicationOwner")
    private List<Publications> personPublicationsList;

    @OneToMany(mappedBy="groupsOwner")
    private List<Groups> personGroupsList;



    public Person(){
        this.degreeField=Strings.EMPTY;
        this.nameField=Strings.EMPTY;
        this.surnameField=Strings.EMPTY;
        this.facultyPictureField=Strings.EMPTY;
        this.workAddresField=Strings.EMPTY;
        this.professionField=Strings.EMPTY;
        this.phoneNumberField=Strings.EMPTY;
        this.usosPersonProfileLinkField=Strings.EMPTY;
        this.hobbyField=Strings.EMPTY;

        //private String fotoField;
        //private String cvField;
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

    public String getDegreeField() {
        return degreeField;
    }

    public void setDegreeField(String degreeField) {
        this.degreeField = degreeField;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getSurnameField() {
        return surnameField;
    }

    public void setSurnameField(String surnameField) {
        this.surnameField = surnameField;
    }

    public String getFacultyPictureField() {
        return facultyPictureField;
    }

    public void setFacultyPictureField(String facultyPictureField) {
        this.facultyPictureField = facultyPictureField;
    }

    public String getWorkAddresField() {
        return workAddresField;
    }

    public void setWorkAddresField(String workAddresField) {
        this.workAddresField = workAddresField;
    }

    public String getProfessionField() {
        return professionField;
    }

    public void setProfessionField(String professionField) {
        this.professionField = professionField;
    }

    public String getPhoneNumberField() {
        return phoneNumberField;
    }

    public void setPhoneNumberField(String phoneNumberField) {
        this.phoneNumberField = phoneNumberField;
    }

    public String getUsosPersonProfileLinkField() {
        return usosPersonProfileLinkField;
    }

    public void setUsosPersonProfileLinkField(String usosPersonProfileLinkField) {
        this.usosPersonProfileLinkField = usosPersonProfileLinkField;
    }

    public String getHobbyField() {
        return hobbyField;
    }

    public void setHobbyField(String hobbyField) {
        this.hobbyField = hobbyField;
    }

    public List<News> getPersonNewsList() {
        return personNewsList;
    }

    public void setPersonNewsList(List<News> personNewsList) {
        this.personNewsList = personNewsList;
    }
    //UWAGA METODAPUBLUCZNA
    public void addNewsToMyList(News news) {
        this.personNewsList.add(news);
        //To po co jest to za bardzo nie wiem
        if (news.getNewsOwner() != this) {
            news.setNewsOwner(this);
        }
    }

    public List<Publications> getPersonPublicationsList() {
        return personPublicationsList;
    }

    public void setPersonPublicationsList(List<Publications> personPublicationsList) {
        this.personPublicationsList = personPublicationsList;
    }
    public void addPubicationsToMyList(Publications publication)
    {
        this.personPublicationsList.add(publication);
        if (publication.getPublicationOwner()!=this)
        {
            publication.setPublicationOwner(this);
        }
    }

    public List<Groups> getPersonGroupsList() {
        return personGroupsList;
    }

    public void setPersonGroupsList(List<Groups> personGroupsList) {
        this.personGroupsList = personGroupsList;
    }
    public void addGroupsToMyList(Groups group)
    {
        this.personGroupsList.add(group);
        if(group.getGroupsOwner()!=this)
        {
            group.setGroupsOwner(this);
        }
    }
}
