package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Callback<P> extends Operation<P, Void> {
    void accept(P parameter) throws Throwable;

    @Override
    default Void call(P parameter) throws Throwable {
        accept(parameter);
        return null;
    }
}
