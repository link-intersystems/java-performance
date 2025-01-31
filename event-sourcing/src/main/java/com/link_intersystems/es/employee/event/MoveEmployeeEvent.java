package com.link_intersystems.es.employee.event;

import java.time.LocalDate;

public class MoveEmployeeEvent extends EmployeeEvent {

    private int departmentNumber;

    public MoveEmployeeEvent(LocalDate eventDate, int employeeNumber, int departmentNumber) {
        super(eventDate, employeeNumber);
        this.departmentNumber = departmentNumber;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }


    @Override
    public void accept(EmployeeEventVisitor visitor) {
        visitor.visit(this);
    }
}
