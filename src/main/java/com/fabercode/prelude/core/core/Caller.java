package com.fabercode.prelude.core;

import java.util.concurrent.Callable;

public class Caller<R> {
    private Callable<R> callable;

    public Caller(Callable<R> callable) {
        this.callable = callable;
    }

    public Result<R> call() {
        return Result.of(callable);
    }

    public static <R> Caller<R> take(R result) {
        return new Caller<>(() -> result);
    }
}
