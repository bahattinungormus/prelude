package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface ProcedureHandle<P> {
    void perform(P parameter);
}
