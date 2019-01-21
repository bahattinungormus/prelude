package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface UnaryExpression<P, R> extends Expression<R> {
    R evaluate(P parameter) throws Throwable;

    @Override
    default R evaluate() throws Throwable {
        return evaluate(null);
    }
}
