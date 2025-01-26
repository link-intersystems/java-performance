package com.link_intersystems.serialization.json;

import com.link_intersystems.serialization.AbstractSerdesTest;
import com.link_intersystems.serialization.Serdes;

import static org.junit.jupiter.api.Assertions.*;

class JacksonSerdesTest extends AbstractSerdesTest {

    @Override
    protected Serdes createSerdes() {
        return new JacksonSerdes();
    }
}