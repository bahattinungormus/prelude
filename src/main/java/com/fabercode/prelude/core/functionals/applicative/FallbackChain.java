package com.fabercode.prelude.core.functionals.applicative;

import com.fabercode.prelude.core.functionals.Functional;
import com.fabercode.prelude.core.functionals.Operation;
import com.fabercode.prelude.core.functionals.Result;

import java.util.Iterator;


public interface FallbackChain<P, R> extends Operation<P, R> {

    default Result<R> getResult(P parameter) {
        try {
            return Result.withValue(call(parameter));
        } catch (Throwable fault) {
            return Result.withError(fault);
        }
    }

    static <P, R> R any(P parameter, Iterator<Functional<P, R>> functionals) {
        if (functionals == null) throw new IllegalArgumentException();
        while (functionals.hasNext()) {
            try {
                R result;
                if ((result = functionals.next().call(parameter)) != null) return result;
            } catch (Throwable fault) {
                if (!functionals.hasNext()) throw new RuntimeException(fault);
            }
        }
        return null;
    }
}
