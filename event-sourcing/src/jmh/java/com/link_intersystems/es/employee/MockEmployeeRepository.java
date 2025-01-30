package com.link_intersystems.es.employee;

import com.link_intersystems.es.employee.events.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.link_intersystems.es.employee.events.Gender.FEMALE;
import static com.link_intersystems.model.TestDataGen.firstnames;
import static com.link_intersystems.model.TestDataGen.lastnames;

public class MockEmployeeRepository implements EmployeeRepository {

    private EmployeeCreator employeeCreator = new EmployeeCreator();

    @Override
    public Employee findEmployee(int employeeNumber) {
        List<EmployeeEvent> employeeEvents = generateEvents(employeeNumber);
        return employeeCreator.apply(employeeEvents);
    }

    private List<EmployeeEvent> generateEvents(int employeeNumber) {
        List<EmployeeEvent> events = new ArrayList<>();


        String firstName = firstnames().get();
        events.add(
                new HireEmployeeEvent.Builder(LocalDate.of(2012, 1, 1))
                        .setEmployeeNumber(employeeNumber)
                        .setHireDate(LocalDate.of(2012, 1, 1))
                        .setFirstName(firstName)
                        .setLastName(lastnames().get())
                        .setGender(FEMALE)
                        .setBirthday(LocalDate.of(1990, 5, 13))
                        .setSalary(2500)
                        .setEmploymentTitle("Junior Web Developer")
                        .setDepartment(3)
                        .build());

        events.add(new MoveEmployeeEvent(LocalDate.of(2012, 9, 1), employeeNumber, 5));
        events.add(new MoveEmployeeEvent(LocalDate.of(2014, 1, 1), employeeNumber, 4));
        events.add(new ChangeSalaryEvent(LocalDate.of(2014, 5, 1), employeeNumber, 3000));
        events.add(new ChangeEmploymentTitleEvent(LocalDate.of(2014, 5, 1), employeeNumber, "Web Developer"));
        events.add(new MoveEmployeeEvent(LocalDate.of(2016, 1, 1), employeeNumber, 10));
        events.add(new ChangeEmploymentTitleEvent(LocalDate.of(2016, 1, 1), employeeNumber, "Backend Developer"));
        events.add(new ChangeSalaryEvent(LocalDate.of(2016, 1, 1), employeeNumber, 3500));
        events.add(new RenameEmployeeEvent(LocalDate.of(2016, 9, 1), employeeNumber, "Jane", "Doe"));
        events.add(new MoveEmployeeEvent(LocalDate.of(2019, 6, 1), employeeNumber, 8));
        events.add(new ChangeEmploymentTitleEvent(LocalDate.of(2019, 6, 1), employeeNumber, "Full-Stack Developer"));
        events.add(new ChangeEmploymentTitleEvent(LocalDate.of(2020, 7, 1), employeeNumber, "Senior Full-Stack Developer"));
        events.add(new ChangeSalaryEvent(LocalDate.of(2020, 7, 1), employeeNumber, 5000));
        events.add(new RenameEmployeeEvent(LocalDate.of(2022, 3, 13), employeeNumber, firstName, lastnames().get()));
        events.add(new MoveEmployeeEvent(LocalDate.of(2022, 9, 1), employeeNumber, 12));

        events.add(new QuitEvent(LocalDate.of(2024, 4, 1), employeeNumber));

        return events;
    }
}
