package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.infrastructure.entity.BaseEntity;
import com.nokia.teachersupport.person.Person;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class News extends BaseEntity {
    private String newsContentField;

    //TODO investigate
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

    public Person getNewsOwner() {
        return newsOwner;
    }

    //TODO investigate
    public void setNewsOwner(Person newsOwner) {
        this.newsOwner = newsOwner;
        if (!newsOwner.getPersonNewsList().contains(this)) {
            newsOwner.getPersonNewsList().add(this);
        }
    }
}

