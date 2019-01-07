package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Generator<R> {
    R generate() throws Throwable;
}
