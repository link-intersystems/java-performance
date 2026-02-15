package com.link_intersystems.es.chess.adapter.ui;

public record BoundedRange<T extends Comparable<T>>(T lowerBound, T upperBound) {

    public BoundedRange {
        if (lowerBound.compareTo(upperBound) > 0) {
            throw new IllegalArgumentException("Lower bound must be smaller or equal to upper bound.");
        }
    }
}
