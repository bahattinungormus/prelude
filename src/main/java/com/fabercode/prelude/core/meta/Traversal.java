package com.fabercode.prelude.core.meta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiFunction;

public class Traversal {
    private final Object root;
    private final String name;
    private final HashSet<Object> objects;
    private final HashMap<String, Object> componentMap;
    private final BiFunction<String, Object, Map<String, ?>> mapper;

    public Traversal(Object root, BiFunction<String, Object, Map<String, ?>> mapper) {
        this.root = root;
        this.name = root.getClass().getSimpleName();
        this.objects = new HashSet<>();
        this.componentMap = new HashMap<>();
        this.mapper = mapper;
        collect(name, root);
    }

    public void collect(String name, Object source) {
        if (source == null || !objects.add(source)) return;
        componentMap.put(name, source);
        mapper.apply(name, source).forEach(this::collect);
    }

    public Object getRoot() {
        return root;
    }

    public Map<String, Object> getComponentMap() {
        return componentMap;
    }

    public String toString() {
        return componentMap.toString();
    }
}
