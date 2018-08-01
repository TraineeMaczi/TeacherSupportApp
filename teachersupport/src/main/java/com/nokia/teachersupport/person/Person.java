package com.nokia.teachersupport.person;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.newsP.News;
import com.nokia.teachersupport.studGroup.StudGroup;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.publications.Publications;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.List;


@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String degreeField;
    private String nameField;
    private String surnameField;

    private String workAddressField;
    private String professionField;
    private String phoneNumberField;
    private String usosPersonProfileLinkField;
    private String hobbyField;

    private String facebookField;
    private String twitterField;

    //private String fotoField;
    //private String cvField;

    @OneToOne(fetch=FetchType.LAZY ,cascade = {CascadeType.ALL})
    @JoinColumn(name="SecurityDataId")
    private UserSecurityData userSecurityDataField;

    @ManyToOne
    @JoinColumn(name = "faculty_id") //to jest nazwa kolumny tylko
    private Faculty facultyField;


    @OneToMany(mappedBy = "newsOwner")
    private List<News> personNewsList;

    @OneToMany(mappedBy = "publicationOwner")
    private List<Publications> personPublicationsList;

    @OneToMany(mappedBy = "groupsOwner")
    private List<StudGroup> personStudGroupList;


    public Person() {
        this.degreeField = Strings.EMPTY;
        this.nameField = Strings.EMPTY;
        this.surnameField = Strings.EMPTY;

        this.workAddressField = Strings.EMPTY;
        this.professionField = Strings.EMPTY;
        this.phoneNumberField = Strings.EMPTY;
        this.usosPersonProfileLinkField = Strings.EMPTY;
        this.hobbyField = Strings.EMPTY;
        this.facebookField=Strings.EMPTY;
        this.twitterField=Strings.EMPTY;

        //private String fotoField;
        //private String cvField;
    }



//    //copy const for user details
//    public Person(Person person) {
//        this.degreeField = person.degreeField;
//        this.nameField = person.nameField;
//        this.surnameField = person.surnameField;
//
//        this.workAddressField = person.workAddressField;
//        this.professionField = person.professionField;
//        this.phoneNumberField = person.phoneNumberField;
//        this.usosPersonProfileLinkField = person.usosPersonProfileLinkField;
//        this.hobbyField = person.hobbyField;
//    }

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



    public String getWorkAddressField() {
        return workAddressField;
    }

    public void setWorkAddressField(String workAddressField) {
        this.workAddressField = workAddressField;
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

    public void addPubicationsToMyList(Publications publication) {
        this.personPublicationsList.add(publication);
        if (publication.getPublicationOwner() != this) {
            publication.setPublicationOwner(this);
        }
    }

    public List<StudGroup> getPersonStudGroupList() {
        return personStudGroupList;
    }

    public void setPersonStudGroupList(List<StudGroup> personStudGroupList) {
        this.personStudGroupList = personStudGroupList;
    }

    public void addGroupsToMyList(StudGroup group) {
        this.personStudGroupList.add(group);
        if (group.getGroupsOwner() != this) {
            group.setGroupsOwner(this);
        }
    }


    public Faculty getFacultyField() {
        return facultyField;
    }

    public void setFacultyField(Faculty facultyField) {
        this.facultyField = facultyField;
        if (!facultyField.getFacultyAndPersonList().contains(this)) {
            facultyField.getFacultyAndPersonList().add(this);
        }
    }

    public UserSecurityData getUserSecurityDataField() {
        return userSecurityDataField;
    }

    public void setUserSecurityDataField(UserSecurityData userSecurityDataField) {
        this.userSecurityDataField = userSecurityDataField;
    }

    public String getFacebookField() {
        return facebookField;
    }

    public void setFacebookField(String facebookField) {
        this.facebookField = facebookField;
    }

    public String getTwitterField() {
        return twitterField;
    }

    public void setTwitterField(String twitterField) {
        this.twitterField = twitterField;
    }
}
