package com.nokia.teachersupport.entity;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@Entity
@Table(name = "securitydata")
public class SecurityData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person personAndSecurity;

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
