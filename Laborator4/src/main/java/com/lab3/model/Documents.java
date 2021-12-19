package com.lab3.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "documents")
@Table(name = "documents")
@NamedQueries({
        @NamedQuery(query = "Select s from documents s", name = "Documents.findAll"),
        @NamedQuery(query = "Select s from documents s where s.author = :author", name = "Documents.findAllByAuthor")
})
public class Documents implements Serializable {


    @Column(name = "author")
    String author;
    @Column(name = "name")
    String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "file")
    private byte[] fileData;

    @Column(name = "content")
    private String content;

    public Documents() {

    }


    public Documents(long registrationNumber, String author, String name, byte[] fileData) {

        this.id = registrationNumber;
        this.author = author;
        this.name = name;
        this.fileData = fileData;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
