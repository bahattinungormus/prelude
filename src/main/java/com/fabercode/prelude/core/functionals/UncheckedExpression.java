package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface UncheckedExpression<R> {
    R evaluate() throws Throwable;
}
