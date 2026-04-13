package com.example.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimitAlgorithm implements RateLimitAlgorithm {

    private static final class CounterState {
        private long windowStartMillis;
        private int count;
    }

    private final Map<RateLimitDimensionKey, CounterState> counters =
            new ConcurrentHashMap<RateLimitDimensionKey, CounterState>();

    @Override
    public boolean isAllowed(String key, RateLimitPolicy policy, long nowMillis, boolean consume) {
        RateLimitDimensionKey dimensionKey = new RateLimitDimensionKey(key, policy);
        CounterState state = counters.computeIfAbsent(dimensionKey, k -> new CounterState());

        synchronized (state) {
            long windowSize = policy.getWindowMillis();
            long currentWindowStart = (nowMillis / windowSize) * windowSize;

            if (state.windowStartMillis != currentWindowStart) {
                state.windowStartMillis = currentWindowStart;
                state.count = 0;
            }

            if (state.count >= policy.getLimit()) {
                return false;
            }

            if (consume) {
                state.count++;
            }
            return true;
        }
    }

    @Override
    public String name() {
        return "FixedWindowCounter";
    }
}
