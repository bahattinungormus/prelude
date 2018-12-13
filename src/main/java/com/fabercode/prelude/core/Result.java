package com.fabercode.prelude.core;

import com.fabercode.prelude.core.functionals.Retrievable;

import java.util.function.Supplier;

public class Result<R> extends Invariant<R> {
    public final Throwable error;

    private Result(R value, Throwable error) {
        super(value);
        this.error = error;
    }

    @SafeVarargs
    static <R> Result<R> of(Retrievable<R>... calls) {
        for (int i = 0; i < calls.length; i++) {
            try {
                R result;
                if ((result = calls[i].retrieve()) != null) return new Result<>(result, null);
            } catch (Throwable cause) {
                if (i == calls.length - 1) return new Result<>(null, cause);
            }
        }
        return new Result<>(null, null);
    }


    @SafeVarargs
    public static <R> Supplier<Result<R>> defer(Retrievable<R>... callChain) {
        return () -> Result.of(callChain);
    }
}
