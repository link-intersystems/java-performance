package com.link_intersystems.serialization;

import com.link_intersystems.model.customer.Customer;
import com.link_intersystems.model.customer.InstancioCustomerSupplier;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractSerdesTest {


    protected abstract Serdes createSerdes();


    @Test
    void serdes() throws IOException {
        Serdes serdes = createSerdes();

        Customer customer = new InstancioCustomerSupplier().get();

        Object deserialized = serdes.deserialize(serdes.serialize(customer));

        assertEquals(customer, deserialized);
    }
}
