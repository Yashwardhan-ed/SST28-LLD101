package com.example.RateLimiter;

public interface RateLimitAlgorithm {
    boolean isAllowed(String key, RateLimitPolicy policy, long nowMillis, boolean consume);

    String name();
}
