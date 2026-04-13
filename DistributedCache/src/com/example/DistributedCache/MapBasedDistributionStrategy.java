package com.example.DistributedCache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapBasedDistributionStrategy<K> implements DistributionStrategy<K> {
    private final Map<K, Integer> keyToNode;
    private final DistributionStrategy<K> fallbackStrategy;

    public MapBasedDistributionStrategy(Map<K, Integer> keyToNode) {
        this(keyToNode, new ModuloHashDistributionStrategy<K>());
    }

    public MapBasedDistributionStrategy(Map<K, Integer> keyToNode, DistributionStrategy<K> fallbackStrategy) {
        this.keyToNode = new HashMap<>(Objects.requireNonNull(keyToNode, "keyToNode cannot be null"));
        this.fallbackStrategy = Objects.requireNonNull(fallbackStrategy, "fallbackStrategy cannot be null");
    }

    @Override
    public int getNodeIndex(K key, int numberOfNodes) {
        if (numberOfNodes <= 0) {
            throw new IllegalArgumentException("numberOfNodes must be greater than 0");
        }

        Objects.requireNonNull(key, "key cannot be null");
        if (keyToNode.containsKey(key)) {
            return Math.floorMod(keyToNode.get(key), numberOfNodes);
        }

        return fallbackStrategy.getNodeIndex(key, numberOfNodes);
    }
}
