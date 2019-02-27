package com.fabercode.prelude.core.functionals;

import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
public interface Operation<P, R> extends Function<P, R> {
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

    @Override
    default R apply(P parameter) {
        return apply(parameter, fault -> {
            throw new RuntimeException(fault);
        });
    }

    default Operation<P, R> or(Operation<P, R> fallback) {
        return parameter -> apply(parameter, fault -> fallback.apply(parameter), fallback);
    }

    static <P, R> Operation<P, R> reduce(Operation<P, R>... operations) {
        return Stream.of(operations).reduce(Operation::or).orElse(p -> null);
    }

    default <T> Operation<P, T> then(Operation<R, T> antecedent) {
        return parameter -> antecedent.apply(apply(parameter));
    }

    default <E> Operation<E, R> before(Operation<E, P> precedent) {
        return parameter -> apply(precedent.apply(parameter));
    }
}
