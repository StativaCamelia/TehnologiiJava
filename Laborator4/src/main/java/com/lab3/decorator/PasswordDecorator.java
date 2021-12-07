package com.lab3.decorator;

import com.lab3.model.Users;
import com.lab3.repo.UserRepoBase;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.text.ParseException;

@Decorator
public class PasswordDecorator implements UserRepoBase {

    @Inject
    @Delegate
    private UserRepoBase decoratedObj;

    @Override
    public int insertUser(Users user) throws ParseException {

        System.out.println("In decorator");
        String password = user.getPassword();
        user.setPassword(password.toUpperCase());
        return decoratedObj.insertUser(user);

    }

    @Override
    public Users login(String username, String password) {

        return decoratedObj.login(username, password);
    }
}
