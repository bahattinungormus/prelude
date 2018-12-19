package com.fabercode.prelude.core;

import com.fabercode.prelude.core.functionals.UncheckedExpression;
import com.fabercode.prelude.core.functionals.UncheckedFunction;

public class Caller<R> {
    private UncheckedExpression<R> callable;

    public Caller(UncheckedExpression<R> callable) {
        this.callable = callable;
    }

    public static <R> Caller<R> bind(UncheckedExpression<R> retrievable) {
        return new Caller<>(retrievable);
    }

    public static <R> Caller<R> take(R result) {
        return new Caller<>(() -> result);
    }

    public <V> Caller<V> then(UncheckedFunction<R, V> uncheckedFunction) {
        return new Caller<>(() -> uncheckedFunction.apply(callable.evaluate()));
    }

    public Result<R> call() {
        return Result.of(callable);
    }
}
