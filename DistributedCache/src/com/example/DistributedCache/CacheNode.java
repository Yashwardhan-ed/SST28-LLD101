package com.example.DistributedCache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> {
    private final int id;
    private final int capacity;
    private final Map<K, V> storage;
    private final EvictionPolicy<K> evictionPolicy;

    public CacheNode(int id, int capacity, EvictionPolicy<K> evictionPolicy) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        this.id = id;
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public int getId() {
        return id;
    }

    public synchronized V get(K key) {
        if (!storage.containsKey(key)) {
            return null;
        }

        evictionPolicy.onGet(key);
        return storage.get(key);
    }

    public synchronized void put(K key, V value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.onPut(key);
            return;
        }

        if (storage.size() >= capacity) {
            K evictKey = evictionPolicy.evictCandidate();
            if (evictKey != null) {
                storage.remove(evictKey);
                evictionPolicy.onRemove(evictKey);
            }
        }

        storage.put(key, value);
        evictionPolicy.onPut(key);
    }

    public synchronized Map<K, V> snapshot() {
        return Collections.unmodifiableMap(new HashMap<>(storage));
    }
}
