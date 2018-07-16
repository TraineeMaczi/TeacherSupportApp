package com.nokia.teachersupport.entity;

import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "meetme")
public class MeetMe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;


    private String place1Field;
    private String office1Field;
    private String day1Field;
    private Time time1FromField;
    private Time time1ToField;

    private String place2Field;
    private String office2Field;
    private String day2Field;
    private Time time2FromField;
    private Time time2ToField;

    public MeetMe() {
        this.place1Field = Strings.EMPTY;
        this.office1Field = Strings.EMPTY;
        this.day1Field = Strings.EMPTY;
        this.time1FromField = new Time(0);
        this.time1ToField = new Time(0);

        this.place2Field = Strings.EMPTY;
        this.office2Field = Strings.EMPTY;
        this.day2Field = Strings.EMPTY;
        this.time2FromField = new Time(0);
        this.time2ToField = new Time(0);
    }

    /*
    @OneToOne
    @JoinColumn(name = "person_id") //tu moze byc ta sama nazwa
    private Person personAndMeetMe;
*/

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

    public String getPlace1Field() {
        return place1Field;
    }

    public void setPlace1Field(String place1Field) {
        this.place1Field = place1Field;
    }

    public String getOffice1Field() {
        return office1Field;
    }

    public void setOffice1Field(String office1Field) {
        this.office1Field = office1Field;
    }

    public String getDay1Field() {
        return day1Field;
    }

    public void setDay1Field(String day1Field) {
        this.day1Field = day1Field;
    }

    public Time getTime1FromField() {
        return time1FromField;
    }

    public void setTime1FromField(Time time1FromField) {
        this.time1FromField = time1FromField;
    }

    public Time getTime1ToField() {
        return time1ToField;
    }

    public void setTime1ToField(Time time1ToField) {
        this.time1ToField = time1ToField;
    }

    public String getPlace2Field() {
        return place2Field;
    }

    public void setPlace2Field(String place2Field) {
        this.place2Field = place2Field;
    }

    public String getOffice2Field() {
        return office2Field;
    }

    public void setOffice2Field(String office2Field) {
        this.office2Field = office2Field;
    }

    public String getDay2Field() {
        return day2Field;
    }

    public void setDay2Field(String day2Field) {
        this.day2Field = day2Field;
    }

    public Time getTime2FromField() {
        return time2FromField;
    }

    public void setTime2FromField(Time time2FromField) {
        this.time2FromField = time2FromField;
    }

    public Time getTime2ToField() {
        return time2ToField;
    }

    public void setTime2ToField(Time time2ToField) {
        this.time2ToField = time2ToField;
    }

    /*
    public Person getPersonAndMeetMe() {
        return personAndMeetMe;
    }

    public void setPersonAndMeetMe(Person personAndMeetMe) {
        this.personAndMeetMe = personAndMeetMe;
    }
    */
}
