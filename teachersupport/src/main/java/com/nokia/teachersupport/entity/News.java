package com.nokia.teachersupport.entity;

import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String newsContentField;

    @OneToMany
    @JoinColumn(name = "person_id")
    private Person personAndNews;

    public News() {
        this.newsContentField = Strings.EMPTY;
    }


    public String getNewsContentField()
    {
        return newsContentField;
    }
    public  void setNewsContentField(String newsText)
    {
        newsContentField =newsText;
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

    public Person getPersonAndNews() {
        return personAndNews;
    }

    public void setPersonAndNews(Person personAndNews) {
        this.personAndNews = personAndNews;
    }
}

