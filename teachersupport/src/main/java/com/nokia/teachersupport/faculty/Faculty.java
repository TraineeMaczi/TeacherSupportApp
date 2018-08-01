package com.nokia.teachersupport.faculty;

import com.nokia.teachersupport.infrastructure.entity.BaseEntity;
import com.nokia.teachersupport.person.Person;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Faculty extends BaseEntity {


    private String facultyNameField;
    //Pole z fotka na razie brak
    //TODO file with image
    private boolean checkedField;
    //TODO check proper mapping
    @OneToMany(mappedBy = "facultyField")
    private List<Person> facultyAndPersonList;   //A tu nie ma new czemu to dzila a przy many to many nie -.-

    public Faculty() {
        this.facultyNameField = Strings.EMPTY;
        this.checkedField = false;
    }

    public String getFacultyNameField() {
        return facultyNameField;
    }

    public void setFacultyNameField(String facultyNameField) {
        this.facultyNameField = facultyNameField;
    }

    public List<Person> getFacultyAndPersonList() {
        return facultyAndPersonList;
    }

    public void setFacultyAndPersonList(List<Person> facultyAndPersonList) {
        this.facultyAndPersonList = facultyAndPersonList;
    }

    //TODO investigate
    public void addPersonToFaculty(Person person) {
        this.facultyAndPersonList.add(person);
        if (person.getFacultyField() != this) {
            person.setFacultyField(this);
        }
    }

    public boolean isCheckedField() {
        return checkedField;
    }

    public void setCheckedField(boolean checkedField) {
        this.checkedField = checkedField;
    }
}