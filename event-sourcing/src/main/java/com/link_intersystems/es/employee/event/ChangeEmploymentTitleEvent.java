package com.link_intersystems.es.employee.event;

import java.time.LocalDate;

public class ChangeEmploymentTitleEvent extends EmployeeEvent {

    private String employmentTitle;

    public ChangeEmploymentTitleEvent(LocalDate eventDate, int employeeNumber, String employmentTitle) {
        super(eventDate, employeeNumber);
        this.employmentTitle = employmentTitle;
    }


    public String getEmploymentTitle() {
        return employmentTitle;
    }

    @Override
    public void accept(EmployeeEventVisitor visitor) {
        visitor.visit(this);
    }
}
