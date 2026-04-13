package com.example.RateLimiter;

public class InternalBusinessService {

    private final RateLimiter rateLimiter;
    private final ExternalProviderClient externalProviderClient;

    public InternalBusinessService(RateLimiter rateLimiter, ExternalProviderClient externalProviderClient) {
        this.rateLimiter = rateLimiter;
        this.externalProviderClient = externalProviderClient;
    }

    public void processRequest(String key, boolean externalCallNeeded) {
        System.out.println("Incoming API request for key=" + key);

        if (!externalCallNeeded) {
            System.out.println("No external call needed. Rate limiter not consulted.");
            return;
        }

        if (rateLimiter.allow(key)) {
            externalProviderClient.callExternal(key);
        } else {
            System.out.println("Rate limit exceeded for key=" + key + ". Handling request gracefully.");
        }
    }
}
