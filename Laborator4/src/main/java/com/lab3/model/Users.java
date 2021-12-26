package com.lab3.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "users")
@Table(name = "users")
@NamedQueries({
        @NamedQuery(query = "Select s from users s", name = "User.findAll"),
        @NamedQuery(query = "Select s from users s where s.name = :username", name = "User.findByUsername")
})
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "ID")
    String id;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "name")
    String name;

    public Users(String id, String password, String email, String name) {

        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public Users() {

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student:" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
