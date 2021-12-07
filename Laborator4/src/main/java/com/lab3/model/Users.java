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
    @Column(name = "id")
    Long id;

    @Column(name = "username")
    String name;

    @Column(name = "password")
    String password;

    @Column(name = "usertype")
    @Enumerated(EnumType.STRING)
    UserTypes userType;

    public Users(String username, String password, UserTypes type) {

        this.name = username;
        this.password = password;
        this.userType = type;
    }

    public Users() {

    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
