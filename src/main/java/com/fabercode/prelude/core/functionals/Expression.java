package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Expression<R> {
    R evaluate() throws Throwable;
}
