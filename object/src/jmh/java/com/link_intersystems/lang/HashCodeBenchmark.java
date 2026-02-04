package com.link_intersystems.lang;

import org.openjdk.jmh.annotations.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;


public class HashCodeBenchmark {

    @State(Scope.Benchmark)
    public static class TestState {

        private Long value;
        private BigInteger bigValue;
        private BigDecimal bigDecimalValue;

        @Setup(Level.Iteration)
        public void setUp() {
            Random random = new Random();
            value = random.nextLong();
            bigValue = BigInteger.valueOf(value);
            bigDecimalValue = BigDecimal.valueOf(value);
        }
    }

    @Benchmark
    public int hashCodeLong(TestState state) {
        return state.value.hashCode();
    }

    @Benchmark
    public int hashCodeBigInteger(TestState state) {
        return state.bigValue.hashCode();
    }

    @Benchmark
    public int hashCodeBigDecimal(TestState state) {
        return state.bigDecimalValue.hashCode();
    }
}
