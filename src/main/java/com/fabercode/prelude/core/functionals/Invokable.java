package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Invokable<P, R> {
    R invoke(P parameter) throws Throwable;
}
