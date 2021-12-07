package com.lab3.decorator;

import com.lab3.model.Documents;
import com.lab3.repo.DocumentsRepoBase;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Decorator
public class SubmissionTime implements DocumentsRepoBase {

    @Inject
    @Delegate
    private DocumentsRepoBase documentsRepoBase;

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
    public void saveDocument(Documents documents) throws UnsupportedEncodingException, ParseException {

        List<Long> timeFrame = getTime();

        if (timeFrame.get(0) < System.currentTimeMillis() && System.currentTimeMillis() < timeFrame.get(1)) {
            documentsRepoBase.saveDocument(documents);
        }

    }
}
