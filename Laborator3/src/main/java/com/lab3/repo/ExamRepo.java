package com.lab3.repo;

import com.lab3.model.Exam;
import com.lab3.model.Time;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamRepo {

    @Resource
    private final DataSource dataSource;

    private Connection con;

    public ExamRepo() throws NamingException {

        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/lab3");

    }

    public Connection getCon() throws SQLException {

        con = dataSource.getConnection();
        return con;
    }

    /**
     * Reds all the exams from the database and returns them
     *
     * @return a list of {@link Exam} entities
     */
    public List<Exam> getAllExams() throws Exception {

        con = getCon();
        List<Exam> examList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from exam");
        while (rs.next()) {

            Exam exam = new Exam();
            exam.setName(rs.getString("name"));
            exam.setDuration(rs.getFloat("duration"));
            exam.setId(rs.getLong("id"));
            exam.setDayInExamSession(rs.getInt("dayInExamSession"));
            Time time = new Time(rs.getInt("hour"), rs.getInt("minute"));
            exam.setHour(time);
            examList.add(exam);
        }

        con.close();
        return examList;
    }

    /**
     * Insert a {@link Exam} into the database
     *
     * @param exam The exam that needs to be saved
     * @param time The time associated with the exam
     */
    public int insertExam(Exam exam, Time time) throws SQLException {

        int result;
        con = getCon();

        PreparedStatement stmt = con.prepareStatement(
                "insert into exam(name, dayInExamSession, hour,minute, duration) values(?,?,?,?,?)");

        stmt.setString(1, exam.getName());
        stmt.setInt(2, exam.getDayInExamSession());
        stmt.setInt(3, time.getHour());
        stmt.setInt(4, time.getMinutes());
        stmt.setFloat(5, exam.getDuration());

        result = stmt.executeUpdate();
        con.close();

        return result;
    }

    /**
     * Gets a list with all the ids for the saved {@link Exam} entities
     *
     * @return a list of ids
     */
    public List<Integer> getExamsIds() throws SQLException, NamingException {

        con = getCon();
        List<Integer> examList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select id from exam");
        while (rs.next()) {

            examList.add(rs.getInt("id"));
        }

        con.close();
        return examList;
    }

}
