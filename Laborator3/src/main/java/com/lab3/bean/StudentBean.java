package com.lab3.bean;

import com.lab3.model.Student;
import com.lab3.repo.StudentRepo;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Bean that will handle the operation regarding a student
 */
@ManagedBean(name = "studentBean")
@RequestScoped
public class StudentBean {

    StudentRepo studentRepo;
    private Map<Long, Student> studentsAsMap;

    public StudentBean() throws NamingException {

        this.studentRepo = new StudentRepo();
    }

    /**
     * Gets a list of students
     *
     * @return a list of {@link Student}s
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Student> getStudentList() throws SQLException, ClassNotFoundException, NamingException {

        return studentRepo.getAllStudent();
    }

    /**
     * Gets the list of student ids saved
     *
     * @return a list of ids
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Integer> getStudentIds() throws SQLException, ClassNotFoundException, NamingException {

        return studentRepo.getAllStudentsIds();
    }

    public Map<Long, Student> getStudentsAsMap() throws SQLException, ClassNotFoundException, NamingException {

        if (studentsAsMap == null) {
            studentsAsMap = getStudentList().stream().collect(Collectors.toMap(Student::getId, student -> student));
        }
        return studentsAsMap;
    }

    /**
     * Saves a student
     *
     * @param student The {@link Student} entity that needs to be saved
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void saveStudent(Student student) throws SQLException, ClassNotFoundException, NamingException {

        int result = studentRepo.insertStudent(student);
        if (result != 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Saved", "The exam was successfully saved"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Not Saved", "The exam was not saved"));
        }
    }


}
