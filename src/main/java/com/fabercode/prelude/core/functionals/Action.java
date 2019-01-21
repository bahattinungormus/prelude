package com.fabercode.prelude.core.functionals;

@FunctionalInterface
public interface Action {
    void perform() throws Throwable;
}
