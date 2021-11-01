package com.lab3.repo;

import com.lab3.model.Student;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo {

    @Resource
    private DataSource dataSource;
    private Connection con;

    public StudentRepo() throws NamingException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/lab3");

    }

    public Connection getCon() throws SQLException {

        con = dataSource.getConnection();
        return con;
    }

    /**
     * Reads and returns all the students from the database
     *
     * @return a list with all the students
     */
    public List<Student> getAllStudent() throws SQLException {

        con = getCon();
        List<Student> studentList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from student");
        while (rs.next()) {

            Student student = new Student();
            student.setName(rs.getString("name"));
            student.setId(rs.getLong("id"));
            studentList.add(student);
        }

        con.close();
        return studentList;
    }

    /**
     * Returns a list with all the student ids
     *
     * @return a list of ids
     */
    public List<Integer> getAllStudentsIds() throws SQLException {

        con = getCon();
        List<Integer> studentList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select id from student");
        while (rs.next()) {

            studentList.add(rs.getInt("id"));
        }

        con.close();
        return studentList;
    }


    /**
     * Stores a {@link Student} into the database
     *
     * @param student the student that needs to be stored
     */
    public int insertStudent(Student student) throws SQLException {

        int result;
        con = getCon();

        PreparedStatement stmt = con.prepareStatement(
                "insert into student(name) values(?)");

        stmt.setString(1, student.getName());

        result = stmt.executeUpdate();
        con.close();

        return result;
    }
}
