package com.link_intersystems.io;

import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardOpenOption.*;

@Fork(warmups = 1, value = 1)
@Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1)
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FileIOBenchmark {

    @State(Scope.Benchmark)
    public static class WriteState {

        protected byte[] content;
        protected Path filepath;

        @Setup
        public void setUp() throws IOException {
            content = new byte[8192];
            new SecureRandom().nextBytes(content);
            filepath = Files.createTempFile("FileIOBenchmark", ".tmp");
        }

        @TearDown
        public void tearDown() throws IOException {
            Files.deleteIfExists(filepath);
        }

        public void write() throws IOException {
            try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(filepath, WRITE, CREATE, DSYNC, SYNC), 8192 * 4);
                 InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(content), 8192 * 4)) {
                inputStream.transferTo(outputStream);
            }
        }
    }


    @Benchmark
    public void write(WriteState state) throws IOException {

        state.write();
    }


    @State(Scope.Benchmark)
    public static class ReadState extends WriteState {


        public void setUp() throws IOException {
            super.setUp();

            write();
        }

    }

    @Benchmark
    public byte[] read(ReadState state) throws IOException {


        try (ByteArrayOutputStream bout = new ByteArrayOutputStream(state.content.length);
             OutputStream outputStream = new BufferedOutputStream(bout, 8192 * 4);
             InputStream inputStream = new BufferedInputStream(Files.newInputStream(state.filepath), 8192 * 4)) {
            inputStream.transferTo(outputStream);
            return bout.toByteArray();
        }
    }

}
