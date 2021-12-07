package com.lab3.repo;

import com.lab3.model.Users;

import java.text.ParseException;

public interface UserRepoBase {

    int insertUser(Users user) throws ParseException;

    Users login(String username, String password);
}
