package com.fabercode.prelude.core.meta;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Reflector {
    public static Stream<Field> fields(Object source) {
        if (source == null) return Stream.empty();
        return Stream.of(source.getClass().getDeclaredFields());
    }

    public static Map<String, Object> fieldValues(Object source) {
        Map<String, Object> map = new HashMap<>();
        fields(source).forEach(f -> {
            Object value = valueOf(source, f);
            if (value != null) map.put(f.getName(), value);
        });
        return map;
    }

    public static Object valueOf(Object source, Field field) {
        try {
            if (!field.isAccessible()) field.setAccessible(true);
            return field.get(source);
        } catch (IllegalAccessException iae) {
            return null;
        }
    }
}
