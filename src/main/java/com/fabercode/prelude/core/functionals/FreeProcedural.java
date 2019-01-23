package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface FreeProcedural extends Procedural<Void> {
    void call() throws Throwable;

    @Override
    default void call(Void parameter) throws Throwable {
        call();
    }
}
