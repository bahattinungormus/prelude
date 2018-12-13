package com.fabercode.prelude.core;

import com.fabercode.prelude.core.functionals.FunctionHandle;
import com.fabercode.prelude.core.functionals.InvocationHandle;

public class Caller<R> {
    private InvocationHandle<R> callable;

    public Caller(InvocationHandle<R> callable) {
        this.callable = callable;
    }

    public static <R> Caller<R> bind(InvocationHandle<R> retrievable) {
        return new Caller<>(retrievable);
    }
    public static <R> Caller<R> take(R result) {
        return new Caller<>(() -> result);
    }

    public <V> Caller<V> then(FunctionHandle<R, V> functionHandle) {
        return new Caller<>(() -> functionHandle.apply(callable.invoke()));
    }

    public Result<R> call() {
        return Result.of(callable);
    }
}
