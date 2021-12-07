package com.lab3.repo;

import com.lab3.model.Exam;
import com.lab3.model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repository class for the relationship between a {@link Student} and an {@link Exam}
 */
@Stateless
public class ExamStudentRepo {

    @PersistenceContext(unitName = "JPAExample")
    private EntityManager em;

    /**
     * Insert in the student_exam table an entity with the student id and the associated exam id
     */
    public void insertExamStudent(Long examId, Integer studentId) {


        Student student1 = em.find(Student.class, Long.valueOf(studentId));
        Exam exam = em.find(Exam.class, examId);

        student1.addExam(exam);
        em.persist(student1);
    }


}
