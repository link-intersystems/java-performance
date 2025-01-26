package com.link_intersystems.model.person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PersonDto implements Serializable {

    private String firstname;
    private String lastname;
    private LocalDate birthday;

    private AddressDto addressDto;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public AddressDto getAddress() {
        return addressDto;
    }

    public void setAddress(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PersonDto personDto = (PersonDto) object;
        return Objects.equals(firstname, personDto.firstname) && Objects.equals(lastname, personDto.lastname) && Objects.equals(birthday, personDto.birthday) && Objects.equals(addressDto, personDto.addressDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, birthday, addressDto);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", address=" + addressDto +
                '}';
    }
}
