package com.link_intersystems.mapping;

import com.link_intersystems.model.person.Person;
import com.link_intersystems.model.person.PersonDto;

public interface PersonMapper {


    PersonDto toDto(Person person) throws Exception;
}
