package com.example.RateLimiter;

import java.time.Clock;
import java.time.Duration;
import java.util.Arrays;

public class Application {

	public static void main(String[] args) {
		InMemoryRateLimitConfigStore configStore = new InMemoryRateLimitConfigStore();
		configStore.setPolicies(
				"T1",
				Arrays.asList(
						new RateLimitPolicy("t1-per-minute", 5, Duration.ofMinutes(1)),
						new RateLimitPolicy("t1-per-hour", 1000, Duration.ofHours(1))
				)
		);

		System.out.println("=== Fixed Window Demo ===");
		runScenario(AlgorithmType.FIXED_WINDOW, configStore);

		System.out.println();
		System.out.println("=== Sliding Window Demo ===");
		runScenario(AlgorithmType.SLIDING_WINDOW, configStore);
	}

	private static void runScenario(AlgorithmType algorithmType, InMemoryRateLimitConfigStore configStore) {
		RateLimiter limiter = RateLimiterFactory.create(algorithmType, configStore, Clock.systemUTC());
		InternalBusinessService businessService = new InternalBusinessService(limiter, new ExternalProviderClient());

		for (int i = 1; i <= 8; i++) {
			// Requests 3 and 6 do not need an external call, so quota should not be consumed.
			boolean externalCallNeeded = (i != 3 && i != 6);
			businessService.processRequest("T1", externalCallNeeded);
		}
	}
}
