package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Mapper<P, R> extends Generator<R> {
    R map(P parameter) throws Throwable;

    @Override
    default R generate() throws Throwable {
        return map(null);
    }
}
