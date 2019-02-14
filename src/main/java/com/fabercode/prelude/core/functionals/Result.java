package com.fabercode.prelude.core.functionals;

import java.util.function.Function;
import java.util.function.Supplier;

public class Result<R> {
    public final R value;
    public final Throwable error;

    private Result(R value, Throwable error) {
        this.value = error == null ? value : null;
        this.error = value == null ? error : null;
    }

    public static <R> Result<R> empty() {
        return new Result<>(null, null);
    }

    public static <R> Result<R> withValue(R value) {
        return new Result<>(value, null);
    }

    public static <R> Result<R> withError(Throwable cause) {
        return new Result<>(null, cause);
    }

    public R yield(Function<Throwable, R> exceptionHandler, Supplier<R> nullHandler) {
        if (isValid()) return value;
        if (isError()) return exceptionHandler.apply(error);
        if (isEmpty()) return nullHandler.get();
        return null;
    }

    public boolean isEmpty() {
        return value == null && error == null;
    }

    public boolean isValid() {
        return value != null;
    }

    public boolean isError() {
        return error != null;
    }
}
