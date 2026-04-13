package com.example.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimitAlgorithm implements RateLimitAlgorithm {

    private static final class SlidingState {
        private boolean initialized;
        private long currentWindowStartMillis;
        private int previousWindowCount;
        private int currentWindowCount;
    }

    private final Map<RateLimitDimensionKey, SlidingState> counters =
            new ConcurrentHashMap<RateLimitDimensionKey, SlidingState>();

    @Override
    public boolean isAllowed(String key, RateLimitPolicy policy, long nowMillis, boolean consume) {
        RateLimitDimensionKey dimensionKey = new RateLimitDimensionKey(key, policy);
        SlidingState state = counters.computeIfAbsent(dimensionKey, k -> new SlidingState());

        synchronized (state) {
            long windowSize = policy.getWindowMillis();
            long currentWindowStart = (nowMillis / windowSize) * windowSize;

            alignWindowState(state, currentWindowStart, windowSize);

            long elapsed = nowMillis - state.currentWindowStartMillis;
            double previousWindowWeight = 1.0d - ((double) elapsed / (double) windowSize);
            if (previousWindowWeight < 0.0d) {
                previousWindowWeight = 0.0d;
            }

            double estimatedCount = state.currentWindowCount + (state.previousWindowCount * previousWindowWeight);
            if (estimatedCount + 1.0d > policy.getLimit()) {
                return false;
            }

            if (consume) {
                state.currentWindowCount++;
            }
            return true;
        }
    }

    private void alignWindowState(SlidingState state, long currentWindowStart, long windowSize) {
        if (!state.initialized) {
            state.initialized = true;
            state.currentWindowStartMillis = currentWindowStart;
            state.previousWindowCount = 0;
            state.currentWindowCount = 0;
            return;
        }

        if (currentWindowStart == state.currentWindowStartMillis) {
            return;
        }

        if (currentWindowStart < state.currentWindowStartMillis) {
            state.currentWindowStartMillis = currentWindowStart;
            state.previousWindowCount = 0;
            state.currentWindowCount = 0;
            return;
        }

        long windowsPassed = (currentWindowStart - state.currentWindowStartMillis) / windowSize;
        if (windowsPassed == 1) {
            state.previousWindowCount = state.currentWindowCount;
        } else {
            state.previousWindowCount = 0;
        }
        state.currentWindowCount = 0;
        state.currentWindowStartMillis = currentWindowStart;
    }

    @Override
    public String name() {
        return "SlidingWindowCounter";
    }
}
