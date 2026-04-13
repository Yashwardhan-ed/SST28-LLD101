package com.example.DistributedCache;

public interface EvictionPolicyFactory<K> {
    EvictionPolicy<K> createPolicy();
}
