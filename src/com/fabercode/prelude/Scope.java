package com.fabercode.prelude;

public class Scope {

    @FunctionalInterface
    public interface Operation<P, R> {
        R perform(P parameter);
    }

    @FunctionalInterface
    public interface Injection<P> {
        void inject(P parameter);
    }

    @FunctionalInterface
    public interface Reduction<R> {
        R performWith(Object... args);
    }

    @FunctionalInterface
    public interface Action {
        void perform();
    }

    @FunctionalInterface
    public interface Production<R> {
        R yield();
    }
}
