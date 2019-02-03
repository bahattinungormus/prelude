package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Actor extends Handler<Void> {
    void call() throws Throwable;

    @Override
    default void call(Void parameter) throws Throwable {
        call();
    }
}
