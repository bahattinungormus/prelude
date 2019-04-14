package com.fabercode.prelude.core.meta.graph;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Graph {
    private final Set<Node> nodeSet;
    private final Function<Object, Map<String, Object>> generator;
    private final BiPredicate<Object, Object> lookupMatcher;
    private final String name;
    private final Node rootNode;
    private boolean generified;

    public Graph(Function<Object, Map<String, Object>> generator, BiPredicate<Object, Object> lookupMatcher, Object root) {
        this.nodeSet = new HashSet<>();
        this.generator = generator;
        this.lookupMatcher = lookupMatcher;
        this.name = root.getClass().getSimpleName();
        this.rootNode = new Node(this, this.name, root);
        this.generified = false;
    }

    public Graph(Function<Object, Map<String, Object>> generator, Object root) {
        this(generator, Objects::equals, root);
    }

    public static Graph fromStream(Function<Object, Stream<Object>> generator, Object root) {
        return new Graph(transform(generator), Objects::equals, root);
    }

    public static Graph fromList(Function<Object, List<Object>> generator, Object root) {
        Function<Object, Map<String, Object>> generator2 = src -> {
            List<Object> objects = generator.apply(src);
            return IntStream.range(0, objects.size()).collect(HashMap::new, (r, i) -> r.put("" + i, objects.get(i)), HashMap::putAll);
        };
        return new Graph(generator2, root);
    }

    private static Function<Object, Map<String, Object>> transform(Function<Object, Stream<Object>> generator) {
        return src -> {
            List<Object> objects = generator.apply(src).collect(Collectors.toList());
            return IntStream.range(0, objects.size()).collect(HashMap::new, (r, i) -> r.put("" + i, objects.get(i)), HashMap::putAll);
        };
    }

    public void generify() {
        if (generified) return;
        generified = true;
        rootNode.generify(generator);
    }

    Optional<Node> lookup(Object object) {
        return nodeSet.stream().filter(node -> lookupMatcher.test(node.value, object)).findAny();
    }

    void add(Node node) {
        nodeSet.add(node);
    }

    @Override
    public String toString() {
        return nodeSet.toString();
    }
}
