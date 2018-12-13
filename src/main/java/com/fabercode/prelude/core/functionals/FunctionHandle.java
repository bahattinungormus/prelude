package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface FunctionHandle<P, R> {
    R apply(P parameter) throws Throwable;
}
