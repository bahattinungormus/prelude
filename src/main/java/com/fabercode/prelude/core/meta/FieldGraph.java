package com.fabercode.prelude.core.meta;

import com.fabercode.prelude.core.meta.graph.Graph;

public class FieldGraph extends Graph {
    public FieldGraph(Object root) {
        super(Reflector::fieldValues, root);
    }
}
