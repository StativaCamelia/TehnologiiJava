package com.lab3.model;

import javax.persistence.*;

@Entity(name = "documents")
@Table(name = "documents")
@NamedQueries({
        @NamedQuery(query = "Select s from documents s", name = "Documents.findAll")
})
public class Documents {


    @Column(name = "author")
    String author;
    @Column(name = "name")
    String name;
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "file")
    private byte[] fileData;

    public Documents() {

    }


    public Documents(long registrationNumber, String author, String name, byte[] fileData) {

        this.id = registrationNumber;
        this.author = author;
        this.name = name;
        this.fileData = fileData;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long registrationNumber) {
        this.id = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'';
    }
}
