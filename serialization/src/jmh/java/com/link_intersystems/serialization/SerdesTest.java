package com.link_intersystems.serialization;

import com.link_intersystems.model.customer.Customer;
import com.link_intersystems.model.customer.InstancioCustomerSupplier;
import org.openjdk.jmh.annotations.*;

import java.util.function.Supplier;

public abstract class SerdesTest {


    @State(Scope.Benchmark)
    public static class SerdesState {
        private Supplier<Customer> customerSupplier = new InstancioCustomerSupplier();

        public Serdes serdes;
        public Customer customer;

        @Setup(Level.Invocation)
        public void setUp() {
            customer = customerSupplier.get();
        }
    }


    @Setup(Level.Trial)
    public void setUpSerdes(SerdesState serdesState) {
        serdesState.serdes = createSerdes();
    }

    protected abstract Serdes createSerdes();

    @Benchmark
    public byte[] serialize(SerdesState serdesState) throws Exception {
        return serdesState.serdes.serialize(serdesState.customer);
    }

    @Benchmark
    public Object serdes(SerdesState serdesState) throws Exception {
        return serdesState.serdes.deserialize(serdesState.serdes.serialize(serdesState.customer));
    }
}
