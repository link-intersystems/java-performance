package com.link_intersystems.lang;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Arrays;

@Fork(warmups = 1, value = 1)
@Measurement(iterations = 1)
@Warmup(iterations = 1)
public class PadRightBenchmark {


    @State(Scope.Benchmark)
    public static class TestState {
        int length;
        String value;

        @Setup(Level.Invocation)
        public void setUp() {
            length = 20;
            value = "dbEXTTRANSAKTION";
        }
    }


    @Benchmark
    public String padRightArray(TestState testState) {
        char[] chars = new char[testState.length];
        Arrays.fill(chars, ' ');
        char[] sourceChars = testState.value.toCharArray();
        System.arraycopy(sourceChars, 0, chars, 0, sourceChars.length);
        return new String(chars);
    }

    @Benchmark
    public String padRightStringBuilder(TestState testState) {

        StringBuilder sb = new StringBuilder(testState.value);

        for (int i = testState.value.length(); i < testState.length; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

    @Benchmark
    public String padRightStringPlusConcat(TestState testState) {
        String value = testState.value;
        for (int i = value.length(); i < testState.length; i++) {
            value = value + " ";
        }
        return value;
    }
}
