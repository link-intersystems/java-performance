package com.link_intersystems.es;

import com.link_intersystems.es.employee.events.Gender;

import java.text.MessageFormat;
import java.time.LocalDate;

public class Employee {

    private int employeeNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private LocalDate hireDate;
    private Gender gender;
    private String employmentTitle;
    private int salary;
    private int departmentNumber;
    private LocalDate quitDate;

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmploymentTitle() {
        return employmentTitle;
    }

    public void setEmploymentTitle(String employmentTitle) {
        this.employmentTitle = employmentTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public void setQuitDate(LocalDate quitDate) {
        this.quitDate = quitDate;
    }

    public LocalDate getQuitDate() {
        return quitDate;
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                '{'
                    "firstName": "{0}"
                    "lastName": "{1}"
                    "birthday" : "{2}"
                    "gender" : "{3}"
                    "hireDate" : "{4}"
                    "employmentTitle": "{5}"
                    "employeeNumber" : {6}
                    "salary" : {7}
                    "departmentNumber" : {8}
                    "quitDate" : "{9}"
                '}'

                """, firstName, lastName, birthday, gender, hireDate, employmentTitle, employeeNumber, salary, departmentNumber, quitDate);
    }
}
