package com.fabercode.prelude.core.meta;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PropertyTraversal extends Traversal {

    public PropertyTraversal(Object root) {
        super(root, PropertyTraversal::flatmap);
    }

    public static HashMap<String, Object> flatmap(String name, Object source) {
        HashMap<String, Object> map = new HashMap<>();
        if (isComposite(source))
            Reflector.propertyMap(source, m -> !m.getDeclaringClass().equals(Object.class))
                    .forEach((key, s) -> map.putAll(decompose(name + "." + key, s)));
        return map;
    }

    private static HashMap<String, ?> decompose(String name, Object source) {
        if (source == null) return new HashMap<>();
        Class sourceType = source.getClass();
        if (sourceType.isArray()) {
            return IntStream.range(0, Array.getLength(source)).collect(
                    HashMap::new,
                    (m, i) -> m.put(name + "[" + i + "]", Array.get(source, i)),
                    HashMap::putAll
            );
        }
        if (Collection.class.isAssignableFrom(sourceType)) return decompose(name, ((Collection) source).toArray());
        if (Map.class.isAssignableFrom(sourceType)) return decompose(name, ((Map) source).values());
        HashMap<String, Object> map = new HashMap<>();
        map.put(name, source);
        return map;
    }

    private static boolean isComposite(Object source) {
        if (source == null) return false;
        Class type = source.getClass();
        return Stream.of(Class.class, String.class,
                Byte.class, Character.class, Short.class, Integer.class, Long.class, Float.class, Double.class,
                byte.class, char.class, short.class, int.class, long.class, float.class, double.class)
                .noneMatch(type::equals);
    }
}
