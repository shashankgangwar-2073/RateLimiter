package com.example;

import java.util.Map;

public class RateLimiterStrategyFactory {
    private final static Map<UserType, RateLimitingStrategy> rateLimiterStrategyMap;
    private final static RateLimiterStrategyFactory rateLimiterStrategyFactoryInstance;

    static {
        rateLimiterStrategyFactoryInstance = new RateLimiterStrategyFactory();
        rateLimiterStrategyMap = Map.of(UserType.FREE, new FixedWindowRateLimiter(), UserType.PRO, new SlidingWindowRateLimiter());
    }

    private RateLimiterStrategyFactory() {
    }

    public static RateLimiterStrategyFactory getInstance() {
        return rateLimiterStrategyFactoryInstance;
    }

    public static RateLimitingStrategy getRateLimitingStrategy(UserType userType) {
        return rateLimiterStrategyMap.get(userType);
    }
}
