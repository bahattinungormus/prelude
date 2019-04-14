package com.fabercode.prelude.core.functionals;

import java.util.function.Function;

@FunctionalInterface
public interface Operation<P, R> {
    R call(P parameter) throws Throwable;

    default Result<R> getResult(P parameter) {
        try {
            return Result.withValue(call(parameter));
        } catch (Throwable fault) {
            return Result.withError(fault);
        }
    }

    default R apply(P parameter, Function<Throwable, R> exceptionHandler, Function<P, R> nullHandler) {
        return getResult(parameter).yield(exceptionHandler, () -> nullHandler.apply(parameter));
    }

    default R apply(P parameter, Function<Throwable, R> exceptionHandler) {
        return apply(parameter, exceptionHandler, p -> null);
    }
}
