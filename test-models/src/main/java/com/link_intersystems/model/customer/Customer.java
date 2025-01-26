package com.link_intersystems.model.customer;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Customer implements Serializable {

    private String firstname;
    private String lastname;
    private String email;

    private Address address;
    private List<PhoneNumber> phoneNumbers;

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Customer customer = (Customer) object;
        return Objects.equals(firstname, customer.firstname) && Objects.equals(lastname, customer.lastname) && Objects.equals(address, customer.address) && Objects.equals(phoneNumbers, customer.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, address, phoneNumbers);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address=" + address +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
