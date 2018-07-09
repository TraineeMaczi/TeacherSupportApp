package com.nokia.teachersupport.entity;

import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@Entity
@Table(name = "content")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String content;

    public News() {
        this.content = Strings.EMPTY;
    }

    public String getContent()
    {
        return content;
    }
    public  void setContent(String newsText)
    {
        content =newsText;
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

}

