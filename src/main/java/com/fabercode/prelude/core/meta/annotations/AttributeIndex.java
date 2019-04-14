package com.fabercode.prelude.core.meta.annotations;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AttributeIndex {
    long value();
}
