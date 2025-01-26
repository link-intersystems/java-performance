package com.link_intersystems.serialization.bin;

import com.link_intersystems.serialization.AbstractSerdesTest;
import com.link_intersystems.serialization.Serdes;

class JavaSerdesTest extends AbstractSerdesTest {

    @Override
    protected Serdes createSerdes() {
        return new JavaSerdes();
    }
}