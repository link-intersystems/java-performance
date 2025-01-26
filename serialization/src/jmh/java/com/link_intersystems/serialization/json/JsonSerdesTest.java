package com.link_intersystems.serialization.json;

import com.link_intersystems.serialization.Serdes;
import com.link_intersystems.serialization.SerdesTest;

public class JsonSerdesTest extends SerdesTest {

    @Override
    protected Serdes createSerdes() {
        return new JacksonSerdes();
    }
}

