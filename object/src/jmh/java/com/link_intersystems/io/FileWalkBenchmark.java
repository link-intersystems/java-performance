package com.link_intersystems.io;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


/**
 * Some code analysis tools say that making fields final improves performance.
 * This test is made to show the impact of both variants so that you can make
 * a decision.
 */
@Fork(warmups = 1, value = 1)
@Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.SECONDS)
public class FileWalkBenchmark {

    @State(Scope.Benchmark)
    public static class FileWalkState {

        private long size;
        private Path directory;

        @Setup
        public void setUp() throws IOException {
            System.err.println();
            directory = Files.createTempDirectory("FileWalkBenchmark");
            System.err.println("Generating data in " + directory);
            DataGenerator dataGenerator = new DataGenerator();
            dataGenerator.setFileCount(10_000);
            dataGenerator.setFileSizeRange(new Range<>(8192, 8192));
            dataGenerator.setDirectoryDepth(new Range<>(2, 2));
            size = dataGenerator.generate(directory);
            System.err.println("Generated " + size + " bytes in " + directory + " (" + formatBytes(size, true, 2) + ")");
        }

        @TearDown
        public void tearDown() throws IOException {
            try (Stream<Path> paths = Files.walk(directory)) {
                paths.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            }
        }

        public void assertSize(long currentSize) {
            if (size != currentSize) {
                throw new IllegalStateException("Size mismatch. Expected: " + size + " Actual: " + currentSize);
            }
        }

        public static String formatBytes(long bytes, boolean iec, int decimals) {
            if (decimals < 0) {
                throw new IllegalArgumentException("decimals must be >= 0");
            }

            final int base = iec ? 1024 : 1000;
            final String[] units = iec
                    ? new String[]{"B", "KiB", "MiB", "GiB", "TiB", "PiB", "EiB"}
                    : new String[]{"B", "kB", "MB", "GB", "TB", "PB", "EB"};

            if (bytes == 0) {
                return "0 B";
            }

            boolean negative = bytes < 0;
            double value = negative ? -(double) bytes : (double) bytes;

            int unit = 0;
            while (value >= base && unit < units.length - 1) {
                value /= base;
                unit++;
            }

            String fmt = "%." + decimals + "f %s";
            String s = String.format(Locale.ROOT, fmt, value, units[unit]);

            // Avoid "-0.0 B" style output for tiny negatives.
            if (negative && !s.startsWith("-")) {
                s = "-" + s;
            }
            return s;
        }
    }


    @Benchmark
    public Object directorySizeStream(FileWalkState assertion) throws IOException {
        long size = checkSize(assertion.directory);
        assertion.assertSize(size);
        return size;
    }

    @Benchmark
    public Object directorySizeVisitor(FileWalkState assertion) throws IOException {
        long size = checkSizeVisitor(assertion.directory);
        assertion.assertSize(size);
        return size;
    }

    public static long checkSize(Path directory) throws IOException {
        long size = 0;
        try (Stream<Path> walk = Files.walk(directory)) {
            size = walk
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            System.out.printf("Failed to get size of %s%n%s", p, e);
                            return 0L;
                        }
                    })
                    .sum();
        } catch (IOException e) {
            System.out.printf("IO errors %s", e);
        }
        return size;
    }

    public static long checkSizeVisitor(Path directory) throws IOException {
        class SizeVisitor extends SimpleFileVisitor<Path> {

            private long size = 0;

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                size += attrs.size();
                return FileVisitResult.CONTINUE;
            }

            public long getSize() {
                return size;
            }
        }

        SizeVisitor sizeVisitor = new SizeVisitor();

        Files.walkFileTree(directory, sizeVisitor);
        return sizeVisitor.getSize();
    }
}
