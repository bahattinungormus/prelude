package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Reactor<R> extends Mapper<Void, R> {
    R apply() throws Throwable;

    @Override
    default R apply(Void parameter) throws Throwable {
        return apply();
    }
}
