package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface UncheckedFunction<P, R> {
    R apply(P parameter) throws Throwable;
}
