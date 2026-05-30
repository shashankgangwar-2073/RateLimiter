package com.example;

public interface RateLimitingStrategy {
    boolean isRateLimited(String userId, int timestamp, int numberOfRequests, int window);
}
