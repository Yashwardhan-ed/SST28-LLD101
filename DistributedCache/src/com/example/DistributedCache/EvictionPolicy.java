package com.example.DistributedCache;

public interface EvictionPolicy<K> {
    void onGet(K key);

    void onPut(K key);

    K evictCandidate();

    void onRemove(K key);
}
