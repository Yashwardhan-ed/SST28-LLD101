package com.example.RateLimiter;

import java.util.Objects;

final class RateLimitDimensionKey {
    private final String key;
    private final String policyName;
    private final int limit;
    private final long windowMillis;

    RateLimitDimensionKey(String key, RateLimitPolicy policy) {
        this.key = key;
        this.policyName = policy.getName();
        this.limit = policy.getLimit();
        this.windowMillis = policy.getWindowMillis();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RateLimitDimensionKey that = (RateLimitDimensionKey) o;
        return limit == that.limit
                && windowMillis == that.windowMillis
                && Objects.equals(key, that.key)
                && Objects.equals(policyName, that.policyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, policyName, limit, windowMillis);
    }
}
