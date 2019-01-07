package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Handler<P> {
    void handle(P parameter) throws Throwable;
}
