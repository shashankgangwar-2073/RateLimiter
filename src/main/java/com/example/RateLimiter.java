package com.example;

public class RateLimiter {
    RateLimiterStrategyFactory rateLimiterFactory;
    int numberOfRequests;
    int timeInterval;
    UserService userService;

    public RateLimiter(int numberOfRequests, int timeInterval) {
        this.numberOfRequests = numberOfRequests;
        this.timeInterval = timeInterval;
        this.rateLimiterFactory = RateLimiterStrategyFactory.getInstance();
        userService = new UserService();
    }

    public boolean isRateLimited(String userId, int timestamp) {
        UserType usertype = userService.userData.get(userId).getType();

        RateLimitingStrategy strategy = rateLimiterFactory.getRateLimitingStrategy(usertype);

        boolean isRateLimited = strategy.isRateLimited(userId, timestamp, numberOfRequests, timeInterval);
        System.out.println(userId + "  " + isRateLimited);
        return isRateLimited;
    }
}
