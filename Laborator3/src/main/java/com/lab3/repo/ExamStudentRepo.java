package com.lab3.repo;

import com.lab3.model.Exam;
import com.lab3.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamStudentRepo {

    private Connection con;

    public ExamStudentRepo() {

    }

    public Connection getCon() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab3", "postgres", "password");
        return con;
    }


    public int insertExamStudent(Integer exam, Integer student) throws SQLException, ClassNotFoundException {

        int result = 0;
        con = getCon();

        PreparedStatement stmt = con.prepareStatement(
                "insert into student_exam(studentId, examId) values(?,?)");

        stmt.setInt(1, student);
        stmt.setInt(2,exam);

        result = stmt.executeUpdate();
        con.close();

        return result;
    }

    public List<String> getExamsNames(Integer studentId) throws SQLException, ClassNotFoundException {

        con = getCon();
        List<String> examList = new ArrayList<>();

        PreparedStatement stmt = con.prepareStatement("select name from exam join student_exam on exam.id = student_exam.examId where student_exam.studentId = ?");
        stmt.setInt(1, studentId);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){

            examList.add(rs.getString("name"));
        }

        con.close();
        return examList;
    }

}
