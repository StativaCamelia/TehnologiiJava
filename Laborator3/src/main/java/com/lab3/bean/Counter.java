package com.lab3.bean;

import com.lab3.model.Exam;
import com.lab3.model.Student;
import com.lab3.repo.ExamRepo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.util.List;


/**
 * Class that iterates throughout the student list and displays one each second by using poll and ajax
 */
@ManagedBean
@ViewScoped
public class Counter {

    private int currentIndex;
    private List<Exam> examList;

    ExamRepo examRepo;

    public Counter() throws Exception {

        this.examRepo = new ExamRepo();
        examList = examRepo.getAllExams();
    }

    public String getExam() {

        return examList.get(currentIndex).getName();
    }

    public int getCurrentIndex() {

        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     * Iterates throughout the student list
     */
    public void increment() {

        if (currentIndex == examList.size() - 1) {

            currentIndex = 0;
        } else {
            currentIndex++;
        }
    }
}
