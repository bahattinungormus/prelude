package com.fabercode.prelude.core.meta.graph;

import com.fabercode.prelude.core.meta.ObjectGraph;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ObjectGraphTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testGenerify() {
        ObjectGraph objectGraph = new ObjectGraph(new ObjectGraph("bahattin"));
        System.out.println(objectGraph);
    }
}
