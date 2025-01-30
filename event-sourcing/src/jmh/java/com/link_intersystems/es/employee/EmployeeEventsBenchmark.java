package com.link_intersystems.es.employee;

import org.openjdk.jmh.annotations.*;

import java.util.Random;

import static java.util.Objects.requireNonNull;

public class EmployeeEventsBenchmark {

    @State(Scope.Benchmark)
    public static class EventState {

        private EmployeeRepository employeeRepository;
        private Random random;

        @Setup(Level.Iteration)
        public void setUp() {
            employeeRepository = new MockEmployeeRepository();
            random = new Random();
        }


    }

    @Benchmark
    public Employee reconstituteEmployee(EventState state) {
        return requireNonNull(state.employeeRepository.findEmployee(state.random.nextInt(10_000)));
    }
}
