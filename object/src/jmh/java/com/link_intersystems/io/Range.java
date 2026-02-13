package com.link_intersystems.io;

public class Range<T extends Comparable<? super T>> {

    private T lowerBound;
    private T upperBound;

    public Range(T lowerBound, T upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        if (lowerBound.compareTo(upperBound) > 0) {
            throw new IllegalArgumentException("Lower bound must be smaller or equal to upper bound.");
        }
    }

    public T getLowerBound() {
        return lowerBound;
    }

    public T getUpperBound() {
        return upperBound;
    }


}
