package com.nokia.teachersupport.entity;

import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String groupNameField;
    private String facultyField;
    private String groupNrFiled;
    private String classNameField;
    private String classDayFiled;
    private Time timeFromField; //Typ time nowy nie wiemn czy odpowiedni
    private Time timeToField;

   /*
    @OneToMany
    @JoinColumn(name = "person_id")
    private Person personAndGroups;
*/

    public Groups() {
        this.groupNameField = Strings.EMPTY;
        this.facultyField = Strings.EMPTY;
        this.groupNrFiled = Strings.EMPTY;
        this.classNameField = Strings.EMPTY;
        this.classDayFiled = Strings.EMPTY;
        //Mozliwe ze te pola beda do zmiany7 bo nie wiem co tam ten zanacznik time zwraca
        this.timeFromField = new Time(0); //to jest ten konstruktor z long w milisekundach bo ten zwykly moze zaostac usunietyt
        this.timeToField = new Time(0);
        //---------------------------------------------------
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

    public String getGroupNameField() {
        return groupNameField;
    }

    public void setGroupNameField(String groupNameField) {
        this.groupNameField = groupNameField;
    }

    public String getFacultyField() {
        return facultyField;
    }

    public void setFacultyField(String facultyField) {
        this.facultyField = facultyField;
    }

    public String getGroupNrFiled() {
        return groupNrFiled;
    }

    public void setGroupNrFiled(String groupNrFiled) {
        this.groupNrFiled = groupNrFiled;
    }

    public String getClassNameField() {
        return classNameField;
    }

    public void setClassNameField(String classNameField) {
        this.classNameField = classNameField;
    }

    public String getClassDayFiled() {
        return classDayFiled;
    }

    public void setClassDayFiled(String classDayFiled) {
        this.classDayFiled = classDayFiled;
    }

    public Time getTimeFromField() {
        return timeFromField;
    }

    public void setTimeFromField(Time timeFromField) {
        this.timeFromField = timeFromField;
    }

    public Time getTimeToField() {
        return timeToField;
    }

    public void setTimeToField(Time timeToField) {
        this.timeToField = timeToField;
    }
/*
    public Person getPersonAndGroups() {
        return personAndGroups;
    }

    public void setPersonAndGroups(Person personAndGroups) {
        this.personAndGroups = personAndGroups;
    }
    */
}
