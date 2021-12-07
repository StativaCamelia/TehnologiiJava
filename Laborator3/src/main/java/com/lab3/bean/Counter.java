package com.lab3.bean;

import com.lab3.model.Exam;
import com.lab3.repo.ExamRepo;

import javax.ejb.EJB;
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

    @EJB
    ExamRepo examRepo;

    public Counter() {


    }

    public String getExam() {

        examList = examRepo.getAllExamsPresentation();
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
