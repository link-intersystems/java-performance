package com.link_intersystems.mapping.mapper.java;

import com.link_intersystems.model.person.Address;
import com.link_intersystems.model.person.Person;
import com.link_intersystems.model.person.AddressDto;
import com.link_intersystems.model.person.PersonDto;
import com.link_intersystems.mapping.PersonMapper;

public class JavaMapper implements PersonMapper {

    public PersonDto toDto(Person person) {
        PersonDto personDto = new PersonDto();

        personDto.setFirstname(person.getFirstname());
        personDto.setLastname(person.getLastname());
        personDto.setBirthday(person.getBirthday());

        AddressDto addressDto = new AddressDto();
        Address address = person.getAddress();
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setZip(address.getZip());

        personDto.setAddress(addressDto);

        return personDto;
    }
}
