package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.person.Person;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String newsContentField;

    @ManyToOne
    @JoinColumn(name = "person_id") //to jest nazwa kolumny tylko
    private Person newsOwner; //newsOwner to jest to co w Person wpisalas mappedby


    public News() {
        this.newsContentField = Strings.EMPTY;
    }


    public String getNewsContentField() {
        return newsContentField;
    }

    public void setNewsContentField(String newsText) {
        newsContentField = newsText;
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


    public Person getNewsOwner() {
        return newsOwner;
    }

    public void setNewsOwner(Person newsOwner) {
        this.newsOwner = newsOwner;
        if (!newsOwner.getPersonNewsList().contains(this)) {
            newsOwner.getPersonNewsList().add(this);
        }
    }


}

