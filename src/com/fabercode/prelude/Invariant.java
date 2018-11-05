package com.fabercode.prelude;

public class Invariant<V> {
    public final V value;

    public Invariant(V value) {
        this.value = value;
    }

    public V get() {
        return value;
    }
}
