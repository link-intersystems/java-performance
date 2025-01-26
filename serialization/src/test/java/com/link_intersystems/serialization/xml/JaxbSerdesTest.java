package com.link_intersystems.serialization.xml;

import com.link_intersystems.serialization.AbstractSerdesTest;
import com.link_intersystems.serialization.Serdes;

class JaxbSerdesTest extends AbstractSerdesTest {

    @Override
    protected Serdes createSerdes() {
        return new JaxbSerdes();
    }
}