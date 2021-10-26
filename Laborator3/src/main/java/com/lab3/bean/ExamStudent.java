package com.lab3.bean;

import com.lab3.model.Exam;
import com.lab3.model.Student;
import com.lab3.repo.ExamStudentRepo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name = "examStudentBean")
@RequestScoped
public class ExamStudent {

    ExamStudentRepo examStudentRepo;
    Integer studentId;
    Integer examId;

    public Integer getExamId() {
        return examId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public ExamStudent() {

        this.examStudentRepo = new ExamStudentRepo();
    }

    public void saveExamStudent() throws SQLException, ClassNotFoundException {

        examStudentRepo.insertExamStudent(examId, studentId);
    }

    public List<String> getExams(Integer studentId) throws SQLException, ClassNotFoundException {

        return examStudentRepo.getExamsNames(studentId);
    }
}
