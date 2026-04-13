package com.example.DistributedCache;

public interface DistributionStrategy<K> {
    int getNodeIndex(K key, int numberOfNodes);
}
