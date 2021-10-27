package com.lab3.repo;

import com.lab3.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo {

    private Connection con;

    public StudentRepo() {

    }

    public Connection getCon() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab3", "postgres", "password");
        return con;
    }

    /**
     * Reads and returns all the students from the database
     * @return
     *      a list with all the students
     */
    public List<Student> getAllStudent() throws SQLException, ClassNotFoundException {

        con = getCon();
        List<Student> studentList = new ArrayList<>();
        Statement stmt= con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from student");
        while(rs.next()){

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
     * @return
     *      a list of ids
     */
    public List<Integer> getAllStudentsIds() throws SQLException, ClassNotFoundException {

        con = getCon();
        List<Integer> studentList = new ArrayList<>();
        Statement stmt= con.createStatement();
        ResultSet rs=stmt.executeQuery("select id from student");
        while(rs.next()){

            studentList.add(rs.getInt("id"));
        }

        con.close();
        return studentList;
    }


    /**
     * Stores a {@link Student} into the database
     * @param student
     *      the student that needs to be stored
     */
    public int insertStudent(Student student) throws SQLException, ClassNotFoundException {

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
