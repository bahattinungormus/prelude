package com.fabercode.prelude.core;

import com.fabercode.prelude.core.functionals.Invokable;
import com.fabercode.prelude.core.functionals.Retrievable;

import java.util.concurrent.Callable;

public class Caller<R> {
    private Retrievable<R> callable;

    public Caller(Retrievable<R> callable) {
        this.callable = callable;
    }

    public static <R> Caller<R> bind(Retrievable<R> retrievable) {
        return new Caller<>(retrievable);
    }
    public static <R> Caller<R> take(R result) {
        return new Caller<>(() -> result);
    }

    public <V> Caller<V> then(Invokable<R, V> invokable) {
        return new Caller<>(() -> invokable.invoke(callable.retrieve()));
    }

    public Result<R> call() {
        return Result.of(callable);
    }
}
