package com.link_intersystems.io;

import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.UUID;

public class DataGenerator {
    private int fileCount = 10_000;
    private Range<Integer> fileSizeRange = new Range<>(1024, 1024 * 32);
    private Range<Integer> directoryDepth = new Range<>(0, 10);
    private Random random = new Random();

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public void setFileSizeRange(Range<Integer> fileSizeRange) {
        this.fileSizeRange = fileSizeRange;
    }

    public void setDirectoryDepth(Range<Integer> directoryDepth) {
        this.directoryDepth = directoryDepth;
    }

    public long generate(Path path) throws IOException {
        int size = 0;
        while (fileCount-- > 0) {
            Path directory = createDirectory(path);
            int fileSize = random.nextInt(fileSizeRange.getLowerBound(), fileSizeRange.getUpperBound() + 1);
            createFile(directory, fileSize);
            size += fileSize;
        }

        return size;

    }

    private void createFile(Path directory, int fileSize) throws IOException {
        Files.createDirectories(directory);
        Path file = directory.resolve(UUID.randomUUID() + ".txt");

        try (ByteChannel byteChannel = Files.newByteChannel(file, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
            byte[] content = new byte[fileSize];
            random.nextBytes(content);
            byteChannel.write(ByteBuffer.wrap(content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path createDirectory(Path path) {
        int depth = random.nextInt(directoryDepth.getLowerBound(), directoryDepth.getUpperBound() + 1);
        while (depth-- > 0) {
            path = randomPath(path);
        }
        return path;
    }

    private @NonNull Path randomPath(Path path) {
        return path.resolve(randomAlphabeticChar());
    }

    private @NonNull String randomAlphabeticChar() {
        return Character.toString((char) random.nextInt(65, 90));
    }


}
