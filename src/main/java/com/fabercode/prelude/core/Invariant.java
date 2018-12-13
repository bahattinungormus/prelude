package com.fabercode.prelude.core;

public class Invariant<V> {
    public final V value;

    public Invariant(V value) {
        this.value = value;
    }

    public final V value() {
        return value;
    }

    public final boolean isEmpty() {
        return value == null;
    }
}
