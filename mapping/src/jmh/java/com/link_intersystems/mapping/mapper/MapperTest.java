package com.link_intersystems.mapping.mapper;

import com.link_intersystems.mapping.PersonMapper;
import com.link_intersystems.model.person.InstancioPersonSupplier;
import com.link_intersystems.model.person.Person;
import com.link_intersystems.model.person.PersonDto;
import org.openjdk.jmh.annotations.*;

import java.util.function.Supplier;

public abstract class MapperTest {

    @State(Scope.Benchmark)
    public static class MapperState {
        private Supplier<Person> personSupplier = new InstancioPersonSupplier();

        public PersonMapper personMapper;
        public Person person;

        @Setup(Level.Invocation)
        public void setUp() {
            person = personSupplier.get();

        }
    }


    @Setup(Level.Trial)
    public void setUpMapper(MapperState mapperState) throws Exception {
        mapperState.personMapper = createMapper();
    }

    protected abstract PersonMapper createMapper() throws Exception;

    @Benchmark
    public PersonDto map(MapperState mapperState) throws Exception {
        return mapperState.personMapper.toDto(mapperState.person);
    }
}
