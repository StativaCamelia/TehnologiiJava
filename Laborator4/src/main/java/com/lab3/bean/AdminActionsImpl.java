package com.lab3.bean;

import com.lab3.log.Log;
import com.lab3.model.Documents;
import com.lab3.repo.DocumentRepoAdminOnlyBase;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Log
@ManagedBean(name = "admin")
@SessionScoped
public class AdminActionsImpl implements AdminActions {

    private Date dateStart;
    private Date endStart;

    @Inject
    private DocumentRepoAdminOnlyBase documentRepoAdminOnlyBase;

    public Date getEndStart() {
        return endStart;
    }

    public void setEndStart(Date endStart) {
        this.endStart = endStart;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }


    @Override
    public void setTimeFrame() {

        try {
            FileWriter myWriter = new FileWriter("timeFrame.txt");

            myWriter.write(dateStart.getTime() + "\n");
            myWriter.write(String.valueOf(endStart.getTime()));

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Documents> viewAllSubmits() throws UnsupportedEncodingException {

        return documentRepoAdminOnlyBase.readDocuments();
    }
}
