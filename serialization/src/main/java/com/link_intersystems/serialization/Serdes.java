package com.link_intersystems.serialization;

import java.io.IOException;

public interface Serdes {


    public byte[] serialize(final Object obj) throws IOException;

    public Object deserialize(final byte[] bytes) throws IOException;
}
