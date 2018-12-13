package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Retrievable<R> {
    R retrieve() throws Throwable;
}
