package com.link_intersystems.es;

import com.link_intersystems.es.employee.events.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.link_intersystems.es.employee.events.Gender.FEMALE;

public class Main {

    public static void main(String[] args) {
        List<EmployeeEvent> employeeEvents = new ArrayList<>();

        employeeEvents.add(
                new HireEmployeeEvent.Builder(LocalDate.of(2012, 1, 1))
                        .setEmployeeNumber(1)
                        .setHireDate(LocalDate.of(2012, 1, 1))
                        .setFirstName("Jane")
                        .setLastName("Smith")
                        .setGender(FEMALE)
                        .setBirthday(LocalDate.of(1990, 5, 13))
                        .setSalary(2500)
                        .setEmploymentTitle("Junior Web Developer")
                        .setDepartment(3)
                        .build());

        employeeEvents.add(new MoveEmployeeEvent(LocalDate.of(2012, 9, 1), 1, 5));
        employeeEvents.add(new MoveEmployeeEvent(LocalDate.of(2014, 1, 1), 1, 4));
        employeeEvents.add(new ChangeSalaryEvent(LocalDate.of(2014, 5, 1), 1, 3000));
        employeeEvents.add(new ChangeEmploymentTitleEvent(LocalDate.of(2014, 5, 1), 1, "Web Developer"));
        employeeEvents.add(new MoveEmployeeEvent(LocalDate.of(2016, 1, 1), 1, 10));
        employeeEvents.add(new ChangeEmploymentTitleEvent(LocalDate.of(2016, 1, 1), 1, "Backend Developer"));
        employeeEvents.add(new ChangeSalaryEvent(LocalDate.of(2016, 1, 1), 1, 3500));
        employeeEvents.add(new RenameEmployeeEvent(LocalDate.of(2016, 9, 1), 1, "Jane", "Doe"));
        employeeEvents.add(new MoveEmployeeEvent(LocalDate.of(2019, 6, 1), 1, 8));
        employeeEvents.add(new ChangeEmploymentTitleEvent(LocalDate.of(2019, 6, 1), 1, "Full-Stack Developer"));
        employeeEvents.add(new ChangeEmploymentTitleEvent(LocalDate.of(2020, 7, 1), 1, "Senior Full-Stack Developer"));
        employeeEvents.add(new ChangeSalaryEvent(LocalDate.of(2020, 7, 1), 1, 5000));
        employeeEvents.add(new RenameEmployeeEvent(LocalDate.of(2022, 3, 13), 1, "Jane", "Smith"));
        employeeEvents.add(new MoveEmployeeEvent(LocalDate.of(2022, 9, 1), 1, 12));
        employeeEvents.add(new QuitEvent(LocalDate.of(2024, 4, 1), 1));


        employeeEvents.sort(EmployeeEvent.TEMPORAL_SORTER);

        EmployeeCreator employeeCreator = new EmployeeCreator();
        Employee employee = employeeCreator.apply(employeeEvents);
        System.out.println(employee);
    }
}
