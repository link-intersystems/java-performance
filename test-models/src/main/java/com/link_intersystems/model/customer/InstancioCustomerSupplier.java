package com.link_intersystems.model.customer;

import org.instancio.Instancio;
import org.instancio.InstancioApi;

import java.util.ArrayList;
import java.util.function.Supplier;

import static com.link_intersystems.model.TestDataGen.*;
import static org.instancio.Select.field;

public class InstancioCustomerSupplier implements Supplier<Customer> {

    private final InstancioApi<Customer> customerGenerator;

    public InstancioCustomerSupplier() {
        customerGenerator = Instancio.of(Customer.class)
                .generate(field(Customer::getFirstname), firstnames())
                .generate(field(Customer::getLastname), lastnames())
                .generate(field(Customer::getEmail), emails())
                .generate(field(Customer::getPhoneNumbers), gen -> gen.collection().subtype(ArrayList.class).size(2))
                .generate(field(PhoneNumber::getNumber), phoneNumbers())
                .generate(field(PhoneNumber::getType), gen -> gen.oneOf("private", "business"))
                .generate(field(Address::getStreet), streets())
                .generate(field(Address::getZip), zipCodes())
                .generate(field(Address::getCity), cities());
    }

    @Override
    public Customer get() {

        return customerGenerator.create();
    }

}
