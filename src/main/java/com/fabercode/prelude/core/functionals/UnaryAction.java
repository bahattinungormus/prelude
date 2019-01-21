package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface UnaryAction<P> extends Action {
    void perform(P parameter) throws Throwable;

    @Override
    default void perform() throws Throwable {
        perform(null);
    }
}
