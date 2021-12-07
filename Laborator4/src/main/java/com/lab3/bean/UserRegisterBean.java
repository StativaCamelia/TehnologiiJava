package com.lab3.bean;

import com.lab3.log.Log;
import com.lab3.model.UserTypes;
import com.lab3.model.Users;
import com.lab3.repo.UserRepoBase;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;

@Log
@ManagedBean(name = "userRegister")
@SessionScoped
public class UserRegisterBean {

    @Inject
    UserRepoBase userRepo;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Size should be more that 6")
    private String password;

    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    private UserTypes type;

    public UserRegisterBean() {
    }

    public void register() throws ParseException {

        System.out.println("Here" + type.getClass().getName());
        userRepo.insertUser(new Users(username, password, type));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserTypes getType() {
        return type;
    }

    public void setType(UserTypes type) {
        this.type = type;
    }
}
