package com.link_intersystems.es.employee.events;

import java.time.LocalDate;

public class RenameEmployeeEvent extends EmployeeEvent {

    private String firstName;
    private String lastName;

    public RenameEmployeeEvent(LocalDate eventDate, int employeeNumber, String firstName, String lastName) {
        super(eventDate, employeeNumber);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public void accept(EmployeeEventVisitor visitor) {
        visitor.visit(this);
    }
}
