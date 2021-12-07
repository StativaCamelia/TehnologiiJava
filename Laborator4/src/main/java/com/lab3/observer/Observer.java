package com.lab3.observer;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

@Stateless
public class Observer {

    public void notifyObserver(@Observes EventSubmit event) {

        System.out.println("Informed about event" + event.getEventMessage());

    }

}
