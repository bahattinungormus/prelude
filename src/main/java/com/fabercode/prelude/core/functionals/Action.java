package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Action extends FunctionDefinition<Void, Void> {
    void run() throws Throwable;

    @Override
    default Void call(Void parameter) throws Throwable {
        run();
        return null;
    }
}
