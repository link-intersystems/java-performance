package com.link_intersystems.serialization;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@State(Scope.Benchmark)
@Fork(warmups = 1, value = 1)
@Measurement(iterations = 2)
@Warmup(iterations = 2)
public class MapVsArrayTest {

    @State(Scope.Benchmark)
    public static class TestState {
        public int valueCount;
        public Map<Long, Long> werte;

        public long[] longs;
        public long index;
    }


    @Setup(Level.Invocation)
    public void setUpSerdes(TestState testState) {
        testState.werte = new HashMap<>();

        testState.valueCount = 100_000;
        Random random = new Random();
        testState.longs = new long[testState.valueCount];
        for (int i = 0; i < testState.valueCount; i++) {
            long l = random.nextLong();
            testState.longs[i] = l;
            testState.werte.put((long) i, l);
        }


        testState.index = random.nextInt(testState.valueCount);

    }


    @Benchmark
    public Object map(TestState testState) throws Exception {
        Long[] values = new Long[testState.valueCount];
        for (int i = 0; i < testState.valueCount; i++) {
            values[i] = testState.werte.get((long) i);
        }
        return values;
    }

    @Benchmark
    public Object array(TestState testState, Blackhole blackhole) throws Exception {
        Long[] values = new Long[testState.valueCount];
        for (int i = 0; i < testState.valueCount; i++) {
            blackhole.consume(i);
        }
        return values;
    }
}
