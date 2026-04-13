package com.example.DistributedCache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryDatabase<K, V> implements Database<K, V> {
    private final ConcurrentMap<K, V> storage = new ConcurrentHashMap<>();

    public void seed(K key, V value) {
        storage.put(key, value);
    }

    @Override
    public V read(K key) {
        return storage.get(key);
    }

    @Override
    public void write(K key, V value) {
        storage.put(key, value);
    }

    public Map<K, V> snapshot() {
        return Collections.unmodifiableMap(new HashMap<>(storage));
    }
}
