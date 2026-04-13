package com.example.RateLimiter;

import java.time.Clock;

public final class RateLimiterFactory {

    private RateLimiterFactory() {
    }

    public static RateLimiter create(
            AlgorithmType algorithmType,
            RateLimitConfigStore configStore,
            Clock clock
    ) {
        RateLimitAlgorithm algorithm;
        switch (algorithmType) {
            case FIXED_WINDOW:
                algorithm = new FixedWindowRateLimitAlgorithm();
                break;
            case SLIDING_WINDOW:
                algorithm = new SlidingWindowRateLimitAlgorithm();
                break;
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithmType);
        }

        return new PluggableRateLimiter(algorithm, configStore, clock);
    }
}
