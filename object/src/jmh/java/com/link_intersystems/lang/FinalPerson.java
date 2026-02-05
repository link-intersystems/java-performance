package com.link_intersystems.lang;

public class FinalPerson {

    private final String firstName;
    private final String lastName;

    public FinalPerson(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
