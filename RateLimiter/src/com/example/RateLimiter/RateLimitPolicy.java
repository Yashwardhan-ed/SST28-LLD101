package com.example.RateLimiter;

import java.time.Duration;

public class RateLimitPolicy {
    private final String name;
    private final int limit;
    private final Duration window;

    public RateLimitPolicy(String name, int limit, Duration window) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Policy name cannot be empty");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be positive");
        }
        if (window == null || window.isZero() || window.isNegative()) {
            throw new IllegalArgumentException("Window must be a positive duration");
        }
        this.name = name;
        this.limit = limit;
        this.window = window;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public Duration getWindow() {
        return window;
    }

    public long getWindowMillis() {
        return window.toMillis();
    }
}
