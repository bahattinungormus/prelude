package com.fabercode.prelude;

import java.util.Objects;

public class Result<R> extends Invariant<R> {
    public final Error error;

    public Result(R value) {
        super(value);
        this.error = null;
    }

    public Result(Error error) {
        super(null);
        this.error = error;
    }

    public R halt() {
        throw Objects.requireNonNull(error);
    }

    public R getOrHalt() {
        return error == null ? get() : halt();
    }
}
