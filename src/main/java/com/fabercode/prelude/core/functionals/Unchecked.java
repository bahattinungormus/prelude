package com.fabercode.prelude.core.functionals;

import java.util.Iterator;
import java.util.stream.Stream;

public interface Unchecked {

    static <P, R> Mapper<P, R> or(Mapper<P, R>... functions) {
        return param -> {
            R result;
            Throwable cause = null;
            for (Mapper<P, R> function : functions) {
                try {
                    if ((result = function.apply(param)) != null) return result;
                } catch (Throwable fault) {
                    cause = fault;
                }
            }
            throw new RuntimeException(cause);
        };
    }

    public static <R> Result<R> resultOf(Iterator<Reactor<R>> fallbacks) {
        R result;
        try {
            if (fallbacks.hasNext() && (result = fallbacks.next().apply()) != null) return Result.value(result);
            else if (fallbacks.hasNext()) return resultOf(fallbacks);
            else return Result.empty();
        } catch (Throwable cause) {
            return fallbacks.hasNext() ? resultOf(fallbacks) : Result.error(cause);
        }
    }

    public static <R> Result<R> resultOf(Reactor<R>... expressions) {
        return resultOf(Stream.of(expressions).iterator());
    }

    public static <T> Class<?> clz(T obj) {
        return obj.getClass();
    }
}
