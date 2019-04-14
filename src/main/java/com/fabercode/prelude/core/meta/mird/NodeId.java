package com.fabercode.prelude.core.meta.mird;

import java.io.Serializable;
import java.util.Objects;

public class NodeId implements Serializable {
    Long entityId;
    Long attributeIndex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeId nodeId = (NodeId) o;
        return Objects.equals(entityId, nodeId.entityId) &&
                Objects.equals(attributeIndex, nodeId.attributeIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityId, attributeIndex);
    }
}
