package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Mapper<P, R> {
    R apply(P parameter) throws Throwable;
}
