package com.example.RateLimiter;

import java.util.List;

public interface RateLimitConfigStore {
    List<RateLimitPolicy> getPolicies(String key);
}
