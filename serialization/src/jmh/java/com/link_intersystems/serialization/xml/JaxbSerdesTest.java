package com.link_intersystems.serialization.xml;

import com.link_intersystems.serialization.Serdes;
import com.link_intersystems.serialization.SerdesTest;

public class JaxbSerdesTest extends SerdesTest {

    @Override
    protected Serdes createSerdes() {
        return new JaxbSerdes();
    }
}
