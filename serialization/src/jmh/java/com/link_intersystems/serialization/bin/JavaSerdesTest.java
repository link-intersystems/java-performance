package com.link_intersystems.serialization.bin;

import com.link_intersystems.serialization.Serdes;
import com.link_intersystems.serialization.SerdesTest;

public class JavaSerdesTest extends SerdesTest {

    @Override
    protected Serdes createSerdes() {
        return new JavaSerdes();
    }
}
