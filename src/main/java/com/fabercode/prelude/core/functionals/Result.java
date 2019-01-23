package com.fabercode.prelude.core.functionals;

import com.fabercode.prelude.core.Caller;
import com.fabercode.prelude.core.Invariant;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Result<R> extends Invariant<R> {
    public final Throwable error;

    private Result(R value, Throwable error) {
        super(value);
        this.error = error;
    }

    public static <R> Result<R> empty() {
        return new Result<>(null, null);
    }

    public static <R> Result<R> error(Throwable cause) {
        return new Result<>(null, cause);
    }

    public static <R> Result<R> value(R value) {
        return new Result<>(value, null);
    }

    @SafeVarargs
    public static <R> Result<R> of(FreeFunctional<R>... expressions) {
        Iterator<FreeFunctional<R>> iterator = Stream.of(expressions).iterator();
        while (iterator.hasNext()) {
            try {
                R result = iterator.next().apply();
                if (result != null) return new Result<>(result, null);
            } catch (Throwable cause) {
                if (!iterator.hasNext()) return new Result<>(null, cause);
            }
        }
        return Caller.resultOf(expressions);
    }


    @SafeVarargs
    public static <R> Supplier<Result<R>> defer(FreeFunctional<R>... expressions) {
        return () -> Result.of(expressions);
    }
}