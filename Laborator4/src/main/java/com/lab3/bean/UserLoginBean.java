package com.lab3.bean;

import com.lab3.log.Log;
import com.lab3.model.Users;
import com.lab3.repo.UserRepoBase;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@Log
@ManagedBean(name = "userLogin")
@SessionScoped
public class UserLoginBean {

    public static final String NAME = "NAME";
    public static final String TYPE = "TYPE";

    @Inject
    UserRepoBase userRepo;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Username cannot be null")
    private String username;

    public UserLoginBean() {
    }

    public String login() {

        Users result = userRepo.login(username, password);
        if (result == null) {

            return "/failure.xhtml";
        }

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .addResponseCookie("user", result.getName(), null);

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .addResponseCookie("type", result.getUserType().getLabel(), null);

        return "/restricted/mainPage.xhtml";

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

}
