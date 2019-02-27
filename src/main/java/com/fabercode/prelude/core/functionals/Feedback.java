package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Feedback<R> extends Operation<Void, R> {
    R get() throws Throwable;

    @Override
    default R call(Void parameter) throws Throwable {
        return get();
    }
}
