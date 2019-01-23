package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Procedural<P> extends Functional<P, Void> {
    void call(P parameter) throws Throwable;

    @Override
    default Void apply(P parameter) throws Throwable {
        call(parameter);
        return null;
    }
}
