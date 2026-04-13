package com.example.RateLimiter;

import java.time.Clock;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PluggableRateLimiter implements RateLimiter {

    private final RateLimitAlgorithm algorithm;
    private final RateLimitConfigStore configStore;
    private final Clock clock;
    private final Map<String, Object> keyLocks = new ConcurrentHashMap<String, Object>();

    public PluggableRateLimiter(RateLimitAlgorithm algorithm, RateLimitConfigStore configStore, Clock clock) {
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm cannot be null");
        }
        if (configStore == null) {
            throw new IllegalArgumentException("Config store cannot be null");
        }
        if (clock == null) {
            throw new IllegalArgumentException("Clock cannot be null");
        }
        this.algorithm = algorithm;
        this.configStore = configStore;
        this.clock = clock;
    }

    @Override
    public boolean allow(String key) {
        List<RateLimitPolicy> policies = configStore.getPolicies(key);
        if (policies.isEmpty()) {
            return true;
        }

        Object keyLock = keyLocks.computeIfAbsent(key, k -> new Object());
        synchronized (keyLock) {
            long nowMillis = clock.millis();

            for (RateLimitPolicy policy : policies) {
                if (!algorithm.isAllowed(key, policy, nowMillis, false)) {
                    return false;
                }
            }

            for (RateLimitPolicy policy : policies) {
                algorithm.isAllowed(key, policy, nowMillis, true);
            }
            return true;
        }
    }
}
