package com.link_intersystems.es.employee.events;

import java.time.LocalDate;

public class ChangeSalaryEvent extends EmployeeEvent {

    private int salary;

    public ChangeSalaryEvent(LocalDate eventDate, int employeeNumber, int salary) {
        super(eventDate, employeeNumber);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public void accept(EmployeeEventVisitor visitor) {
        visitor.visit(this);
    }
}
