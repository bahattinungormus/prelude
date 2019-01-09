package com.fabercode.prelude.core;

import com.fabercode.prelude.core.functionals.Expression;

import java.util.Iterator;
import java.util.stream.Stream;

public class Caller<R> {
    public static <R> Result<R> resultOf(Iterator<Expression<R>> fallbacks) {
        R result;
        try {
            if (fallbacks.hasNext() && (result = fallbacks.next().evaluate()) != null) return Result.value(result);
            else if (fallbacks.hasNext()) return resultOf(fallbacks);
            else return Result.empty();
        } catch (Throwable cause) {
            return fallbacks.hasNext() ? resultOf(fallbacks) : Result.error(cause);
        }
    }

    public static <R> Result<R> resultOf(Expression<R>... expressions) {
        return resultOf(Stream.of(expressions).iterator());
    }
}
