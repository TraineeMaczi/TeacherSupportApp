package com.nokia.teachersupport.fileUpload;

import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.studGroup.StudGroup;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name = "pic")
    private byte[] pic;
    @ManyToOne
    private StudGroup filesOfGroup;
    public FileModel() {
    }

    public FileModel(String name, String type, byte[] pic) {
        this.name = name;
        this.type = type;
        this.pic = pic;

    }

    public StudGroup getFilesOfGroup() {
        return filesOfGroup;
    }

    public void setFilesOfGroup(StudGroup filesOfGroup) {
        this.filesOfGroup = filesOfGroup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPic() {
        return this.pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}