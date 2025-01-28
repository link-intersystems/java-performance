package com.link_intersystems.es.employee.events;

import java.time.LocalDate;

public class QuitEvent extends EmployeeEvent {

    public QuitEvent(LocalDate eventDate, int employeeNumber) {
        super(eventDate, employeeNumber);
    }

    @Override
    public void accept(EmployeeEventVisitor visitor) {
        visitor.visit(this);
    }
}
