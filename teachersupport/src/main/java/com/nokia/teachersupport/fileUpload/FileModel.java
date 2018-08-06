package com.nokia.teachersupport.fileUpload;

import com.nokia.teachersupport.person.Person;

import javax.persistence.*;

@Entity
@Table(name="files")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name="pic")
    private byte[] pic;
    @ManyToOne
    Person filesOwner;

    public FileModel(){}

    public FileModel(String name,String type, byte[] pic, Person filesOwner){
        this.name = name;
        this.type=type;
        this.pic = pic;
        this.filesOwner=filesOwner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Person getFilesOwner() {
        return filesOwner;
    }

    public void setFilesOwner(Person filesOwner) {
        this.filesOwner = filesOwner;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public byte[] getPic(){
        return this.pic;
    }

    public void setPic(byte[] pic){
        this.pic = pic;
    }
}