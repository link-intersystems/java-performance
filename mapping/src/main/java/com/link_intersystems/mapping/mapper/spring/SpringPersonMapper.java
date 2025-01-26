package com.link_intersystems.mapping.mapper.spring;

import com.link_intersystems.mapping.PersonMapper;
import com.link_intersystems.model.person.Person;
import com.link_intersystems.model.person.AddressDto;
import com.link_intersystems.model.person.PersonDto;
import org.springframework.beans.BeanUtils;

public class SpringPersonMapper implements PersonMapper {
    @Override
    public PersonDto toDto(Person person) {
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(person, personDto);
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(person.getAddress(), addressDto);
        personDto.setAddress(addressDto);
        return personDto;
    }
}
