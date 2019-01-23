package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Functional<P, R> {
    R apply(P parameter) throws Throwable;
}
