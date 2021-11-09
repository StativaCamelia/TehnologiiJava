package com.lab3.repo;

import com.lab3.model.Exam;
import com.lab3.model.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ExamRepoTest {

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private ExamRepo examRepo;

    @BeforeEach
    public void setUp() {

        entityManager = Mockito.mock(EntityManager.class);
        entityTransaction = Mockito.mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(entityTransaction);
        examRepo = new ExamRepo(entityManager);
    }

    @Test
    public void getExamById() {

        //setup
        Exam expected = new Exam();
        expected.setId(1L);

        when(entityManager.find(eq(Exam.class), anyLong())).thenReturn(expected);

        //execute
        Long resultedExam = examRepo.getById(1L).getId();

        //verify
        assertEquals(1, resultedExam);
    }

    @Test
    public void insertExam() {

        //setup
        Exam expected = new Exam();
        Time time = new Time();

        expected.setHour(time);
        //execute
        examRepo.insertExam(expected, time);

        //verify
        verify(entityManager).persist(eq(expected));
    }

    @Test
    public void deleteExam() {

        Exam expected = new Exam();

        //execute
        examRepo.deleteExam(expected);

        //verify
        verify(entityManager).remove(any());
    }

    @Test
    void update() {

        //setup
        Exam expected = new Exam();
        expected.setName("Exam");

        when(entityManager.find(eq(Exam.class), anyLong())).thenReturn(expected);
        //execute
        examRepo.update(expected);

        //verify
        verify(entityManager).persist(eq(expected));
    }
}