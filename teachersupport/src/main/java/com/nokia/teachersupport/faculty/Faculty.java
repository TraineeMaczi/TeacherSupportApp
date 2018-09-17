package com.nokia.teachersupport.faculty;

import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.person.Person;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.List;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String facultyNameField;
    //Pole z fotka na razie brak


    @OneToMany(mappedBy = "facultyField")
    private List<Person> facultyAndPersonList;   //A tu nie ma new czemu to dzila a przy many to many nie -.-
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private FileModel file;

    public Faculty() {
        this.facultyNameField = Strings.EMPTY;

    }

    public FileModel getFile() {
        return file;
    }

    public void setFile(FileModel file) {
        this.file = file;
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

    public void addPersonToFaculty(Person person) {
        this.facultyAndPersonList.add(person);
        if (person.getFacultyField() != this) {
            person.setFacultyField(this);
        }
    }


}