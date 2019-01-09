package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface UnaryProcedure<P> {
    void call(P parameter) throws Throwable;
}
