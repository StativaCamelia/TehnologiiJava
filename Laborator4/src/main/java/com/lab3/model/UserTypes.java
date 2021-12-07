package com.lab3.model;

public enum UserTypes {

    ADMIN("Admin"),
    REVIEWER("Reviewer"),
    BASIC("Basic");

    private final String label;

    UserTypes(String label) {

        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
