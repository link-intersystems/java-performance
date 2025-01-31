package com.link_intersystems.es.employee.event;

import java.time.LocalDate;

public class HireEmployeeEvent extends EmployeeEvent {


    public static class Builder {
        private LocalDate eventDate;
        private int employeeNumber;
        private String firstName;
        private String lastName;
        private LocalDate birthday;
        private LocalDate hireDate;
        private Gender gender;
        private String employmentTitle;
        private int salary;
        private int departmentNumber;

        public Builder(LocalDate eventDate) {
            this.eventDate = eventDate;
        }

        public Builder setEmployeeNumber(int employeeNumber) {
            this.employeeNumber = employeeNumber;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setHireDate(LocalDate hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder setEmploymentTitle(String employmentTitle) {
            this.employmentTitle = employmentTitle;
            return this;
        }

        public Builder setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        public Builder setDepartment(int departmentNumber) {
            this.departmentNumber = departmentNumber;
            return this;
        }

        public HireEmployeeEvent build() {
            return new HireEmployeeEvent(eventDate, employeeNumber, firstName, lastName, birthday, hireDate, gender, employmentTitle, salary, departmentNumber);
        }
    }

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private LocalDate hireDate;
    private Gender gender;
    private final String employmentTitle;
    private final int salary;
    private final int departmentNumber;

    private HireEmployeeEvent(LocalDate eventDate, int employeeNumber, String firstName, String lastName, LocalDate birthday, LocalDate hireDate, Gender gender, String employmentTitle, int salary, int departmentNumber) {
        super(eventDate, employeeNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hireDate = hireDate;
        this.gender = gender;
        this.employmentTitle = employmentTitle;
        this.salary = salary;
        this.departmentNumber = departmentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getEmploymentTitle() {
        return employmentTitle;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    @Override
    public void accept(EmployeeEventVisitor visitor) {
        visitor.visit(this);
    }
}
