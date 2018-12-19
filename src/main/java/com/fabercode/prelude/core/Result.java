package com.fabercode.prelude.core;

import com.fabercode.prelude.core.functionals.UncheckedExpression;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Result<R> extends Invariant<R> {
    public final Throwable error;

    private Result(R value, Throwable error) {
        super(value);
        this.error = error;
    }

    @SafeVarargs
    public static <R> Result<R> of(UncheckedExpression<R>... expressions) {
        Iterator<UncheckedExpression<R>> iterator = Stream.of(expressions).iterator();
        while (iterator.hasNext()) {
            try {
                R result = iterator.next().evaluate();
                if (result != null) return new Result<>(result, null);
            } catch (Throwable cause) {
                if (!iterator.hasNext()) return new Result<>(null, cause);
            }
        }
        return new Result<>(null, null);
    }


    @SafeVarargs
    public static <R> Supplier<Result<R>> defer(UncheckedExpression<R>... expressions) {
        return () -> Result.of(expressions);
    }
}
