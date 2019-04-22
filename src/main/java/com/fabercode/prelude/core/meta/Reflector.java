package com.fabercode.prelude.core.meta;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Reflector {
    public static Object call(Object source, Method method, Object... parameters) {
        try {
            if (!method.isAccessible()) method.setAccessible(true);
            return method.invoke(source, parameters);
        } catch (Throwable e) {
            return null;
        }
    }

    public static Stream<Method> gettersOf(Object source) {
        return Stream.of(source.getClass().getMethods())
                .filter(m -> m.getParameterCount() == 0 && m.getName().matches("^get[A-Z].*"));
    }

    public static Map<String, Object> propertyMap(Object source) {
        HashMap<String, Object> map = new HashMap<>();
        if (source == null) return map;
        gettersOf(source).forEach(getter -> {
            Object value = call(source, getter);
            if (value != null) map.put(getter.getName(), value);

        });
        return map;
    }

    public static Map<String, Object> propertyMap(Object source, Predicate<Method> isPropertyGetter) {
        HashMap<String, Object> map = new HashMap<>();
        if (source == null) return map;
        gettersOf(source).filter(isPropertyGetter).forEach(getter -> {
            Object value = call(source, getter);
            if (value != null) map.put(getter.getName(), value);

        });
        return map;
    }
}
