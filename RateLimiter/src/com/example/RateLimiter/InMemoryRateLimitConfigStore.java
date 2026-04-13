package com.example.RateLimiter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRateLimitConfigStore implements RateLimitConfigStore {
    private final Map<String, List<RateLimitPolicy>> policiesByKey = new ConcurrentHashMap<String, List<RateLimitPolicy>>();

    public void setPolicies(String key, List<RateLimitPolicy> policies) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Rate limiting key cannot be empty");
        }
        if (policies == null) {
            throw new IllegalArgumentException("Policies cannot be null");
        }

        List<RateLimitPolicy> copy = Collections.unmodifiableList(new ArrayList<RateLimitPolicy>(policies));
        policiesByKey.put(key, copy);
    }

    @Override
    public List<RateLimitPolicy> getPolicies(String key) {
        List<RateLimitPolicy> policies = policiesByKey.get(key);
        if (policies == null) {
            return Collections.emptyList();
        }
        return policies;
    }
}
