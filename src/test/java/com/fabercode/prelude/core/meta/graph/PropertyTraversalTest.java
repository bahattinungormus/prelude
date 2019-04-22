package com.fabercode.prelude.core.meta.graph;

import com.fabercode.prelude.core.meta.PropertyTraversal;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PropertyTraversalTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testGenerify() {
        PropertyTraversal propertyTraversal = new PropertyTraversal(new PropertyTraversal("bahattin"));
        System.out.println(propertyTraversal);
    }
}
