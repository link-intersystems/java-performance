package com.link_intersystems.mapping.mapper.dozer;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.link_intersystems.mapping.PersonMapper;
import com.link_intersystems.model.person.Person;
import com.link_intersystems.model.person.PersonDto;

import static java.util.Objects.requireNonNull;

public class DozserPersonMapper implements PersonMapper {

    private Mapper mapper;

    public DozserPersonMapper() {
        this(DozerBeanMapperBuilder.buildDefault());
    }

    public DozserPersonMapper(Mapper mapper) {
        this.mapper = requireNonNull(mapper);
    }

    @Override
    public PersonDto toDto(Person person) {

        return mapper.map(person, PersonDto.class);
    }
}
