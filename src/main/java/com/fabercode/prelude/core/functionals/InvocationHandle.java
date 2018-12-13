package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface InvocationHandle<R> {
    R invoke() throws Throwable;
}
