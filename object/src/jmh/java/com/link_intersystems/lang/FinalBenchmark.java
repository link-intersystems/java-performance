package com.link_intersystems.lang;

import org.instancio.Instancio;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;


/**
 * Some code analysis tools say that making fields final improves performance.
 * This test is made to show the impact of both variants so that you can make
 * a decision.
 */
@Fork(warmups = 1, value = 1)
@Measurement(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class FinalBenchmark {

    @State(Scope.Benchmark)
    public static class FinalPersonState {

        private FinalPerson finalPerson;

        @Setup(Level.Invocation)
        public void setUp() {
            finalPerson = Instancio.create(FinalPerson.class);
        }
    }

    @State(Scope.Benchmark)
    public static class NoneFinalPersonState {

        private NoneFinalPerson noneFinalPerson;

        @Setup(Level.Invocation)
        public void setUp() {
            noneFinalPerson = Instancio.create(NoneFinalPerson.class);
        }
    }


    @Benchmark
    public String finalPerson(FinalPersonState state) {
        return state.finalPerson.getFullName();
    }

    @Benchmark
    public String noneFinalPerson(NoneFinalPersonState state) {

        return state.noneFinalPerson.getFullName();
    }
}
