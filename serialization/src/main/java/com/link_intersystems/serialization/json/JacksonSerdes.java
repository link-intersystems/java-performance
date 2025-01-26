package com.link_intersystems.serialization.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.link_intersystems.serialization.Serdes;
import com.link_intersystems.model.customer.Customer;

import java.io.IOException;

public class JacksonSerdes implements Serdes {

    private ObjectMapper objectMapper = new ObjectMapper();

    public JacksonSerdes() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public byte[] serialize(Object obj) throws IOException {
        return objectMapper.writeValueAsBytes(obj);
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException {
        return objectMapper.readValue(bytes, Customer.class);
    }
}
