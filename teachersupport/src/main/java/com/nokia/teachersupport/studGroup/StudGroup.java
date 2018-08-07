package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.person.Person;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
public class StudGroup {
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
    private String timeFromFieldH;
    private String timeToFieldH;
    private String timeFromFieldM;
    private String timeToFieldM;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person groupsOwner;

    @OneToMany(mappedBy = "resourceOwner")
    private List<GroupResources> groupsResourcesList;


    public StudGroup() {
        this.groupNameField = Strings.EMPTY;
        this.facultyField = Strings.EMPTY;
        this.groupNrFiled = Strings.EMPTY;
        this.classNameField = Strings.EMPTY;
        this.classDayFiled = Strings.EMPTY;
        this.timeFromFieldH=Strings.EMPTY;
        this.timeToFieldH=Strings.EMPTY;
        this.timeFromFieldM=Strings.EMPTY;
        this.timeToFieldM=Strings.EMPTY;

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



    public Person getGroupsOwner() {
        return groupsOwner;
    }

    public void setGroupsOwner(Person groupsOwner) {
        this.groupsOwner = groupsOwner;
        if (!groupsOwner.getPersonStudGroupList().contains(this)) {
            groupsOwner.getPersonStudGroupList().add(this);
        }
    }


    public List<GroupResources> getGroupsResourcesList() {
        return groupsResourcesList;
    }

    public void setGroupsResourcesList(List<GroupResources> groupsResourcesList) {
        this.groupsResourcesList = groupsResourcesList;
    }

    public void addResourcesToMyList(GroupResources groupResource) {
        this.groupsResourcesList.add(groupResource);

        if (groupResource.getResourceOwner() != this) {
            groupResource.setResourceOwner(this);
        }
    }

    public String getTimeFromFieldH() {
        return timeFromFieldH;
    }

    public void setTimeFromFieldH(String timeFromFieldH) {
        this.timeFromFieldH = timeFromFieldH;
    }

    public String getTimeToFieldH() {
        return timeToFieldH;
    }

    public void setTimeToFieldH(String timeToFieldH) {
        this.timeToFieldH = timeToFieldH;
    }

    public String getTimeFromFieldM() {
        return timeFromFieldM;
    }

    public void setTimeFromFieldM(String timeFromFieldM) {
        this.timeFromFieldM = timeFromFieldM;
    }

    public String getTimeToFieldM() {
        return timeToFieldM;
    }

    public void setTimeToFieldM(String timeToFieldM) {
        this.timeToFieldM = timeToFieldM;
    }
}
