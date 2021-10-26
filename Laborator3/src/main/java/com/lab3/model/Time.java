package com.lab3.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name="time")
@RequestScoped
public class Time implements Serializable {

    private int hour;

    private int minutes;

    public Time(){

    }

    public Time(int hour, int minute){

        this.hour = hour;
        this.minutes = minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
