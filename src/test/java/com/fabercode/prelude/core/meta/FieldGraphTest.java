package com.fabercode.prelude.core.meta;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldGraphTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testGenerify() {
        String source = "bahattin";
        FieldGraph fieldGraph = new FieldGraph(source);
        fieldGraph.generify();
        System.out.println(fieldGraph);
    }
}
