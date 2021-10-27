package com.lab3.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Model class for an exam entity
 */
@ManagedBean(name="exam")
@RequestScoped
public class Exam implements Serializable {

    private Long id;

    private String name;

    private Integer dayInExamSession;

    private Time hour;

    private Float duration;

    public Exam() {

    }


    public void setHour(Time hour) {
        this.hour = hour;
    }

    public Time getHour() {
        return hour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Integer getDayInExamSession() {
        return dayInExamSession;
    }

    public void setDayInExamSession(Integer dayInExamSession) {
        this.dayInExamSession = dayInExamSession;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
