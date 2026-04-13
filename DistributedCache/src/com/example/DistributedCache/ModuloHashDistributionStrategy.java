package com.example.DistributedCache;

import java.util.Objects;

public class ModuloHashDistributionStrategy<K> implements DistributionStrategy<K> {
    @Override
    public int getNodeIndex(K key, int numberOfNodes) {
        if (numberOfNodes <= 0) {
            throw new IllegalArgumentException("numberOfNodes must be greater than 0");
        }

        Objects.requireNonNull(key, "key cannot be null");
        return Math.floorMod(key.hashCode(), numberOfNodes);
    }
}
