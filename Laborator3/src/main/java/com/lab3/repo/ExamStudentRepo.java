package com.lab3.repo;

import com.lab3.model.Exam;
import com.lab3.model.Student;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for the relatioship beetween a {@link Student} and an {@link Exam}
 */
public class ExamStudentRepo {

    private Connection con;
    @Resource
    private DataSource dataSource;

    public ExamStudentRepo() throws NamingException {

        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/lab3");

    }


    public Connection getCon() throws SQLException {

        con = dataSource.getConnection();
        return con;
    }


    /**
     * Insert in the student_exam table an entity with the student id and the associated exam id
     */
    public void insertExamStudent(Integer exam, Integer student) throws SQLException {

        con = getCon();

        PreparedStatement stmt = con.prepareStatement(
                "insert into student_exam(studentId, examId) values(?,?)");

        stmt.setInt(1, student);
        stmt.setInt(2, exam);

        con.close();

    }


    /**
     * Gets all the exams names associated with a studentID
     */
    public List<String> getExamsNames(Integer studentId) throws SQLException {

        con = getCon();
        List<String> examList = new ArrayList<>();

        PreparedStatement stmt = con.prepareStatement("select name from exam join student_exam on exam.id = student_exam.examId where student_exam.studentId = ?");
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            examList.add(rs.getString("name"));
        }

        con.close();
        return examList;
    }

}
