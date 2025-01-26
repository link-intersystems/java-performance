package com.link_intersystems.model.person;

import org.instancio.Instancio;
import org.instancio.InstancioApi;

import java.util.function.Supplier;

import static com.link_intersystems.model.TestDataGen.*;
import static org.instancio.Select.field;

public class InstancioPersonSupplier implements Supplier<Person> {

    private final InstancioApi<Person> personGenerator;

    @Override
    public Person get() {
        return personGenerator.create();
    }


    public InstancioPersonSupplier() {
        InstancioApi<Address> addressGenerator = Instancio.of(Address.class)
                .generate(field(Address::getCity), cities())
                .generate(field(Address::getStreet), streets())
                .generate(field(Address::getZip), zipCodes());

        personGenerator = Instancio.of(Person.class)
                .generate(field(Person::getFirstname), firstnames())
                .generate(field(Person::getLastname), lastnames())
                .generate(field(Person::getBirthday), Instancio.gen().temporal().localDate().past())
                .setModel(field(Person::getAddress), addressGenerator.toModel())
        ;

    }
}
