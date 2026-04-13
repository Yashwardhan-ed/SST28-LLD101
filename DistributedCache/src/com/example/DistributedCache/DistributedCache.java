package com.example.DistributedCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DistributedCache<K, V> implements Cache<K, V> {
    private final List<CacheNode<K, V>> nodes;
    private final DistributionStrategy<K> distributionStrategy;
    private final Database<K, V> database;

    public DistributedCache(
            int numberOfNodes,
            int nodeCapacity,
            DistributionStrategy<K> distributionStrategy,
            EvictionPolicyFactory<K> evictionPolicyFactory,
            Database<K, V> database) {
        if (numberOfNodes <= 0) {
            throw new IllegalArgumentException("numberOfNodes must be greater than 0");
        }

        this.distributionStrategy = Objects.requireNonNull(distributionStrategy, "distributionStrategy cannot be null");
        this.database = Objects.requireNonNull(database, "database cannot be null");

        List<CacheNode<K, V>> createdNodes = new ArrayList<>();
        for (int index = 0; index < numberOfNodes; index++) {
            EvictionPolicy<K> policy = Objects.requireNonNull(
                    evictionPolicyFactory.createPolicy(),
                    "evictionPolicyFactory must return a non-null policy");
            createdNodes.add(new CacheNode<>(index, nodeCapacity, policy));
        }
        this.nodes = Collections.unmodifiableList(createdNodes);
    }

    @Override
    public V get(K key) {
        CacheNode<K, V> targetNode = resolveNode(key);
        V cachedValue = targetNode.get(key);
        if (cachedValue != null) {
            return cachedValue;
        }

        V dbValue = database.read(key);
        if (dbValue != null) {
            targetNode.put(key, dbValue);
        }

        return dbValue;
    }

    @Override
    public void put(K key, V value) {
        database.write(key, value);
        CacheNode<K, V> targetNode = resolveNode(key);
        targetNode.put(key, value);
    }

    public int getNodeIndex(K key) {
        return distributionStrategy.getNodeIndex(key, nodes.size());
    }

    public Map<Integer, Map<K, V>> snapshot() {
        Map<Integer, Map<K, V>> cacheView = new LinkedHashMap<>();
        for (CacheNode<K, V> node : nodes) {
            cacheView.put(node.getId(), node.snapshot());
        }
        return Collections.unmodifiableMap(cacheView);
    }

    private CacheNode<K, V> resolveNode(K key) {
        int nodeIndex = distributionStrategy.getNodeIndex(key, nodes.size());
        return nodes.get(nodeIndex);
    }
}
