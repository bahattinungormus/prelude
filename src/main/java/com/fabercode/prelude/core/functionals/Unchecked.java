package com.fabercode.prelude.core.functionals;

import java.util.Iterator;
import java.util.stream.Stream;

public interface Unchecked {

    static <P, R> Functional<P, R> or(Functional<P, R>... functionals) {
        return param -> {
            R result;
            Throwable cause = null;
            for (Functional<P, R> functional : functionals) {
                try {
                    if ((result = functional.apply(param)) != null) return result;
                } catch (Throwable fault) {
                    cause = fault;
                }
            }
            throw new RuntimeException(cause);
        };
    }

    public static <R> Result<R> resultOf(Iterator<FreeFunctional<R>> fallbacks) {
        R result;
        try {
            if (fallbacks.hasNext() && (result = fallbacks.next().apply()) != null) return Result.value(result);
            else if (fallbacks.hasNext()) return resultOf(fallbacks);
            else return Result.empty();
        } catch (Throwable cause) {
            return fallbacks.hasNext() ? resultOf(fallbacks) : Result.error(cause);
        }
    }

    public static <R> Result<R> resultOf(FreeFunctional<R>... expressions) {
        return resultOf(Stream.of(expressions).iterator());
    }

    public static <T> Class<?> clz(T obj) {
        return obj.getClass();
    }
}
