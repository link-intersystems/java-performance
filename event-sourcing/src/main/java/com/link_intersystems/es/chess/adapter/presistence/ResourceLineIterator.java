package com.link_intersystems.es.chess.adapter.presistence;

import java.io.*;
import java.util.Iterator;

class ResourceLineIterator implements Iterator<String>, Closeable {

    private final BufferedReader lineReader;
    private String nextLine;

    public ResourceLineIterator(ClassLoader classLoader, String resource) {
        InputStream resourceAsStream = classLoader.getResourceAsStream(resource);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resource);
        }
        lineReader = new BufferedReader(new InputStreamReader(resourceAsStream));
    }

    @Override
    public void close() throws IOException {
        lineReader.close();
    }

    @Override
    public boolean hasNext() {
        if (nextLine == null) {
            try {
                nextLine = lineReader.readLine();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return nextLine != null;
    }

    @Override
    public String next() {
        try {
            return nextLine;
        } finally {
            nextLine = null;
        }
    }
}
