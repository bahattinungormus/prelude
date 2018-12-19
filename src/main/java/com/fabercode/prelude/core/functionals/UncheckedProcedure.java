package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface UncheckedProcedure<P> {
    void call(P parameter);
}
