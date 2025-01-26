package com.link_intersystems.model.person;

import java.io.Serializable;
import java.util.Objects;

public class AddressDto implements Serializable {

    private String street;
    private String city;
    private String zip;

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AddressDto addressDto = (AddressDto) object;
        return Objects.equals(street, addressDto.street) && Objects.equals(city, addressDto.city) && Objects.equals(zip, addressDto.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, zip);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
