package com.lab3.bean;

import com.lab3.log.Log;
import com.lab3.model.UserTypes;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@Log
@ManagedBean(name = "userTypes")
@ApplicationScoped
public class UserTypesBean {

    public UserTypes[] getTypes() {
        return UserTypes.values();
    }

}
