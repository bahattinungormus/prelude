package com.fabercode.prelude.core.meta.graph;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testGenerify() {
        Graph graph = Graph.fromList(s -> {
            String str = s.toString();
            List<Object> characters = new ArrayList<>();
            for (char c : str.toCharArray()) {
                characters.add(c);
            }
            return characters;
        }, "bahattin");
        graph.generify();
        System.out.println(graph);
    }
}
