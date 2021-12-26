package com.lab3.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "groups")
@Table(name = "groups")
@NamedQueries({
        @NamedQuery(query = "Select s from groups s", name = "Groups.findAll")
})
public class Groups implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "ID")
    String id;

    @Column(name = "name")
    String name;

    public Groups(String id, String name) {

        this.id = id;
        this.name = name;
    }

    public Groups() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Student:" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
