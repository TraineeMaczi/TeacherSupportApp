package com.nokia.teachersupport.person;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class MeetMe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;


    private String placeField;
    private String officeField;
    private String dayField;
//    private String timeFromFieldH;
//    private String timeToFieldH;
//    private String timeFromFieldM;
//    private String timeToFieldM;
    private String timeField;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person meetMeOwner;


    @Autowired
    public MeetMe()
    {
        this.placeField=Strings.EMPTY;
        this.officeField=Strings.EMPTY;
        this.dayField=Strings.EMPTY;
//        this.timeFromFieldH=Strings.EMPTY;
//        this.timeToFieldH=Strings.EMPTY;
//       this.timeFromFieldM=Strings.EMPTY;
//         this.timeToFieldM=Strings.EMPTY;
         this.timeField=Strings.EMPTY;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getPlaceField() {
        return placeField;
    }

    public void setPlaceField(String placeField) {
        this.placeField = placeField;
    }

    public String getOfficeField() {
        return officeField;
    }

    public void setOfficeField(String officeField) {
        this.officeField = officeField;
    }

    public String getDayField() {
        return dayField;
    }

    public void setDayField(String dayField) {
        this.dayField = dayField;
    }

//    public String getTimeFromFieldH() {
//        return timeFromFieldH;
//    }

//    public void setTimeFromFieldH(String timeFromFieldH) {
//        this.timeFromFieldH = timeFromFieldH;
//    }
//
//    public String getTimeToFieldH() {
//        return timeToFieldH;
//    }
//
//    public void setTimeToFieldH(String timeToFieldH) {
//        this.timeToFieldH = timeToFieldH;
//    }
//
//    public String getTimeFromFieldM() {
//        return timeFromFieldM;
//    }
//
//    public void setTimeFromFieldM(String timeFromFieldM) {
//        this.timeFromFieldM = timeFromFieldM;
//    }
//
//    public String getTimeToFieldM() {
//        return timeToFieldM;
//    }
//
//    public void setTimeToFieldM(String timeToFieldM) {
//        this.timeToFieldM = timeToFieldM;
//    }

    public String getTimeField() {
        return timeField;
    }

    public void setTimeField(String timeField) {
        this.timeField = timeField;
    }


    public Person getMeetMeOwner() {
        return meetMeOwner;
    }

    public void setMeetMeOwner(Person meetMeOwner) {
        this.meetMeOwner = meetMeOwner;
    }
}
