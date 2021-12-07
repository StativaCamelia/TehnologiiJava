package com.lab3.bean;

import com.lab3.model.Exam;
import com.lab3.model.Student;
import com.lab3.repo.ExamStudentRepo;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Bean for the operations regarding the relation between a student and an exam
 */
@ManagedBean(name = "examStudentBean")
@RequestScoped
public class ExamStudent {

    @EJB
    ExamStudentRepo examStudentRepo;
    Integer studentId;
    Long examId;

    public Long getExamId() {
        return examId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setExamId(Long examId) {

        this.examId = examId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public ExamStudent() {

    }

    /**
     * Saves the relationship between a {@link Student} and a {@link Exam}
     *
     */
    public void saveExamStudent() {

        examStudentRepo.insertExamStudent(examId, studentId);
    }


}
