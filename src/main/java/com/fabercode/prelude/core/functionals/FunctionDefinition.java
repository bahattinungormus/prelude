package com.fabercode.prelude.core.functionals;

import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
public interface FunctionDefinition<P, R> extends Function<P, R> {
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

    default FunctionDefinition<P, R> or(FunctionDefinition<P, R> fallback) {
        return parameter -> apply(parameter, fault -> fallback.apply(parameter), fallback);
    }

    static <P, R> FunctionDefinition<P, R> reduce(FunctionDefinition<P, R>... functionDefinitions) {
        return Stream.of(functionDefinitions).reduce(FunctionDefinition::or).orElse(p -> null);
    }

    default <T> FunctionDefinition<P, T> then(FunctionDefinition<R, T> antecedent) {
        return parameter -> antecedent.apply(apply(parameter));
    }

    default <E> FunctionDefinition<E, R> before(FunctionDefinition<E, P> precedent) {
        return parameter -> apply(precedent.apply(parameter));
    }
}
