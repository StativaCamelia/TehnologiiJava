package com.lab3.decorator;

import com.lab3.model.Users;
import com.lab3.repo.UserRepoBase;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Decorator
public class RegistrationTime implements UserRepoBase {

    @Inject
    @Delegate
    private UserRepoBase decoratedObj;

    private List<Long> getTime() throws ParseException {

        BufferedReader reader;
        List<Long> dateList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(
                    "timeFrame.txt"));
            long startDate = Long.parseLong(reader.readLine());
            long endDate = Long.parseLong(reader.readLine());

            dateList.add(startDate);
            dateList.add(endDate);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dateList;
    }

    @Override
    public int insertUser(Users user) throws ParseException {
        List<Long> timeFrame = getTime();

        if (timeFrame.get(0) < System.currentTimeMillis() && System.currentTimeMillis() < timeFrame.get(1)) {
            return decoratedObj.insertUser(user);
        }

        return 1;
    }

    @Override
    public Users login(String username, String password) {
        return decoratedObj.login(username, password);
    }
}
