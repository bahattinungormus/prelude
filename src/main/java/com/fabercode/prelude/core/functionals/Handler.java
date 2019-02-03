package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Handler<P> extends Mapper<P, Void> {
    void call(P parameter) throws Throwable;

    @Override
    default Void apply(P parameter) throws Throwable {
        call(parameter);
        return null;
    }
}
