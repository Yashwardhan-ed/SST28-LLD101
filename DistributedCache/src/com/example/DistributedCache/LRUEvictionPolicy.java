package com.example.DistributedCache;

import java.util.LinkedHashSet;
import java.util.Set;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final Set<K> accessOrder = new LinkedHashSet<>();

    @Override
    public void onGet(K key) {
        accessOrder.remove(key);
        accessOrder.add(key);
    }

    @Override
    public void onPut(K key) {
        accessOrder.remove(key);
        accessOrder.add(key);
    }

    @Override
    public K evictCandidate() {
        if (accessOrder.isEmpty()) {
            return null;
        }

        K lruKey = accessOrder.iterator().next();
        accessOrder.remove(lruKey);
        return lruKey;
    }

    @Override
    public void onRemove(K key) {
        accessOrder.remove(key);
    }
}
