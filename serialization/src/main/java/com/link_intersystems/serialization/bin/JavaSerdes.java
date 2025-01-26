package com.link_intersystems.serialization.bin;

import com.link_intersystems.serialization.Serdes;

import java.io.*;

public class JavaSerdes implements Serdes {
    @Override
    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (ObjectOutputStream oout = new ObjectOutputStream(bout)) {
            oout.writeObject(obj);
        }
        return bout.toByteArray();
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        try (ObjectInputStream oin = new ObjectInputStream(bin)) {
            return oin.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }
}
