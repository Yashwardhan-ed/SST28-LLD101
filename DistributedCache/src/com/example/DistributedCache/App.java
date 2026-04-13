package com.example.DistributedCache;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        InMemoryDatabase<String, String> database = new InMemoryDatabase<>();
        database.seed("A", "Alpha-from-db");
        database.seed("Z", "Zulu-from-db");

        Map<String, Integer> keyRouting = new HashMap<>();
        keyRouting.put("A", 0);
        keyRouting.put("B", 0);
        keyRouting.put("C", 0);

        DistributionStrategy<String> strategy = new MapBasedDistributionStrategy<>(keyRouting);

        DistributedCache<String, String> cache = new DistributedCache<>(
                3,
                2,
                strategy,
                new EvictionPolicyFactory<String>() {
                    @Override
                    public EvictionPolicy<String> createPolicy() {
                        return new LRUEvictionPolicy<>();
                    }
                },
                database);

        System.out.println("Initial cache state: " + cache.snapshot());

        System.out.println("GET A (cache miss, load from db): " + cache.get("A"));
        System.out.println("Cache after GET A: " + cache.snapshot());

        cache.put("B", "Beta");
        System.out.println("PUT B -> Cache: " + cache.snapshot());

        cache.put("C", "Charlie");
        System.out.println("PUT C causes LRU eviction on node 0 -> Cache: " + cache.snapshot());

        System.out.println("GET A after eviction (miss on node, fetch from db): " + cache.get("A"));
        System.out.println("Cache after reloading A: " + cache.snapshot());

        System.out.println("GET Z (routed by fallback modulo strategy): " + cache.get("Z"));
        System.out.println("Final cache state: " + cache.snapshot());
        System.out.println("Database state (write-through): " + database.snapshot());
    }
}
