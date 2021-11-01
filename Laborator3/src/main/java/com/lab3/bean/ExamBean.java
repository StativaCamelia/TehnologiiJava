package com.lab3.bean;

import com.lab3.model.Exam;
import com.lab3.model.Time;
import com.lab3.repo.ExamRepo;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Bean for the operations regarding a student
 */
@ManagedBean(name = "examBean")
@RequestScoped
public class ExamBean implements Serializable {

    ExamRepo examRepo;

    public ExamBean() throws NamingException {

        this.examRepo = new ExamRepo();
    }

    /**
     * Gets all the {@link Exam}s from the database
     *
     * @return a list of {@link Exam}s
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Exam> getExamList() throws Exception {

        return examRepo.getAllExams();
    }

    /**
     * Gets the ids of the {@link Exam} saved in the database
     *
     * @return a list of ids
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Integer> getExamIds() throws SQLException, ClassNotFoundException, NamingException {

        return examRepo.getExamsIds();
    }

    /**
     * Saves an {@link Exam} entity
     *
     * @param exam The exam that needs to be saved
     * @param time The time for the exam
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void saveExam(Exam exam, Time time) throws SQLException, ClassNotFoundException, NamingException {

        int result = examRepo.insertExam(exam, time);
        if (result != 0) {
            FacesContext.getCurrentInstance().addMessage("form:Create", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Saved", "The exam was successfully saved"));
        } else {
            FacesContext.getCurrentInstance().addMessage("form:Create", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Not Saved", "The exam was not saved"));
        }
    }
}
