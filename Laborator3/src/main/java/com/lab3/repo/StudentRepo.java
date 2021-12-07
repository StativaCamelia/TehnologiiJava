package com.lab3.repo;

import com.lab3.model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StudentRepo {

    @PersistenceContext(unitName = "JPAExample")
    private EntityManager em;


    /**
     * Reads and returns all the students from the database
     *
     * @return a list with all the students
     */
    public List getAllStudent() {

        Query query = em.createNamedQuery("Student.findAll");
        return query.getResultList();
    }

    /**
     * Returns a list with all the student ids
     *
     * @return a list of ids
     */
    public List<Integer> getAllStudentsIds() {

        Query query = em.createNamedQuery("Student.getIds");
        return (List<Integer>) query.getResultList();
    }


    /**
     * Stores a {@link Student} into the database
     *
     * @param student the student that needs to be stored
     */
    public int insertStudent(Student student) {

        int result = 0;

        em.persist(student);

        return result;
    }

    public void deleteStudent(Student student) {

        if (!em.contains(student)) {
            student = em.merge(student);
        }

        em.remove(student);
    }

    public void update(Student student) {

        Student oldStudent = em.find(Student.class, student);
        oldStudent.setName(student.getName());

        em.persist(oldStudent);
    }
}
