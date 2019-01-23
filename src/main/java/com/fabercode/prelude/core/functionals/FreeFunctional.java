package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface FreeFunctional<R> extends Functional<Void, R> {
    R apply() throws Throwable;

    @Override
    default R apply(Void parameter) throws Throwable {
        return apply();
    }
}
