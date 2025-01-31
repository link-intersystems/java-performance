package com.link_intersystems.es.employee.event;

import java.time.LocalDate;
import java.util.Comparator;

public abstract class EmployeeEvent {

    public static Comparator<EmployeeEvent> TEMPORAL_SORTER = (o1, o2) -> o1.getEventDate().compareTo(o2.getEventDate());

    private LocalDate eventDate;
    private int employeeNumber;

    public EmployeeEvent(LocalDate eventDate, int employeeNumber) {
        this.eventDate = eventDate;
        this.employeeNumber = employeeNumber;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public abstract void accept(EmployeeEventVisitor visitor);
}
