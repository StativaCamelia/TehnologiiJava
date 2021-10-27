package com.lab3.bean;

import com.lab3.model.Student;
import com.lab3.repo.StudentRepo;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.List;

/**
 * Bean that will handle the operation regarding a student
 */
@ManagedBean(name="studentBean")
@RequestScoped
public class StudentBean {

    StudentRepo studentRepo;

	public StudentBean(){

        this.studentRepo = new StudentRepo();
    }

    /**
     * Gets a list of students
     * @return
     *      a list of {@link Student}s
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Student> getStudentList() throws SQLException, ClassNotFoundException {

        return studentRepo.getAllStudent();
    }

    /**
     * Gets the list of student ids saved
     * @return
     *      a list of ids
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Integer> getStudentIds() throws SQLException, ClassNotFoundException {

        return studentRepo.getAllStudentsIds();
    }

    /**
     * Saves a student
     * @param student
     *      The {@link Student} entity that needs to be saved
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void saveStudent(Student student) throws SQLException, ClassNotFoundException {

        int result = studentRepo.insertStudent(student);
        if(result != 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Saved", "The exam was successfully saved"));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Not Saved", "The exam was not saved"));
        }
    }


}
