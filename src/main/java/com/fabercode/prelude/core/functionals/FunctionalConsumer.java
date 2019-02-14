package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface FunctionalConsumer<P> extends Functional<P, Void> {
    void accept(P parameter) throws Throwable;

    @Override
    default Void call(P parameter) throws Throwable {
        accept(parameter);
        return null;
    }
}
