package com.fabercode.prelude.core.functionals;

import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
public interface Functional<P, R> extends Function<P, R> {
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

    default Functional<P, R> or(Functional<P, R> fallback) {
        return parameter -> apply(parameter, fault -> fallback.apply(parameter), fallback);
    }

    static <P, R> Functional<P, R> reduce(Functional<P, R>... functionals) {
        return Stream.of(functionals).reduce(Functional::or).orElse(p -> null);
    }

    default <T> Functional<P, T> then(Functional<R, T> antecedent) {
        return parameter -> antecedent.apply(apply(parameter));
    }

    default <E> Functional<E, R> before(Functional<E, P> precedent) {
        return parameter -> apply(precedent.apply(parameter));
    }
}
