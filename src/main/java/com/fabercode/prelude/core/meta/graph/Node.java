package com.fabercode.prelude.core.meta.graph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Node {
    private final Graph graph;
    public final Node parent;
    public final String name;
    public final Object value;
    public Set<Node> children;
    public boolean generified;

    private Node(Graph graph, Node parent, String name, Object value) {
        this.graph = graph;
        this.parent = parent;
        this.name = name;
        this.value = value;
        this.children = null;
        this.generified = false;
        graph.add(this);
    }

    public Node(Graph graph, String name, Object value) {
        this(graph, null, name, value);
    }

    public void generify(Function<Object, Map<String, Object>> generator) {
        if (this.generified) return;
        this.generified = true;
        this.children = new HashSet<>();
        generator.apply(value).forEach((name, value) -> children.add(wrap(name, value)));
        this.children.forEach(n -> n.generify(generator));
    }

    public Node wrap(String name, Object value) {
        return graph.lookup(value).orElseGet(() -> new Node(graph, this, name, value));
    }

    @Override
    public String toString() {
        return "{" + name + ":" + value + '}';
    }
}
