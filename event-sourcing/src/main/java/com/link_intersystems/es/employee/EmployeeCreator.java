package com.link_intersystems.es.employee;

import com.link_intersystems.es.employee.events.*;

public class EmployeeCreator extends EmployeeEventApply {

    @Override
    protected Employee doVisit(HireEmployeeEvent hireEmployeeEvent) {
        Employee employee = new Employee();
        employee.setEmployeeNumber(hireEmployeeEvent.getEmployeeNumber());
        employee.setFirstName(hireEmployeeEvent.getFirstName());
        employee.setLastName(hireEmployeeEvent.getLastName());
        employee.setBirthday(hireEmployeeEvent.getBirthday());
        employee.setDepartmentNumber(hireEmployeeEvent.getDepartmentNumber());
        employee.setEmploymentTitle(hireEmployeeEvent.getEmploymentTitle());
        employee.setSalary(hireEmployeeEvent.getSalary());
        employee.setHireDate(hireEmployeeEvent.getHireDate());
        employee.setGender(hireEmployeeEvent.getGender());
        return employee;
    }

    @Override
    protected void doVisit(ChangeEmploymentTitleEvent changeEmploymentTitleEvent, Employee employee) {
        employee.setEmploymentTitle(changeEmploymentTitleEvent.getEmploymentTitle());
    }

    @Override
    protected void doVisit(ChangeSalaryEvent changeSalaryEvent, Employee employee) {
        employee.setSalary(changeSalaryEvent.getSalary());
    }

    @Override
    protected void doVisit(MoveEmployeeEvent moveEmployeeEvent, Employee employee) {
        employee.setDepartmentNumber(moveEmployeeEvent.getDepartmentNumber());
    }

    @Override
    protected void doVisit(RenameEmployeeEvent renameEmployeeEvent, Employee employee) {
        employee.setFirstName(renameEmployeeEvent.getFirstName());
        employee.setLastName(renameEmployeeEvent.getLastName());
    }

    @Override
    protected void doVisit(QuitEvent quitEvent, Employee employee) {
        employee.setQuitDate(quitEvent.getEventDate());
    }


}
