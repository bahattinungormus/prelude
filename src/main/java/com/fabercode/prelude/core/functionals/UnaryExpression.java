package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface UnaryExpression<P, R> {
    R evaluate(P parameter) throws Throwable;
}
