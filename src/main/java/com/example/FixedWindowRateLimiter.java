package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter implements RateLimitingStrategy {
    Map<String, Integer> fixedWindow;
    Map<String, Integer> windowStartTime;

    public FixedWindowRateLimiter() {
        fixedWindow = new ConcurrentHashMap<>();
        windowStartTime = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isRateLimited(String userId, int timestamp, int numberOfRequests, int rateLimitingWindow) {

        if (!fixedWindow.containsKey(userId)) {
            fixedWindow.put(userId, 0);
            windowStartTime.put(userId, 0);
        }
        int currWindowStartTime = windowStartTime.get(userId);

        if (currWindowStartTime + rateLimitingWindow < timestamp) {
            fixedWindow.put(userId, 0);
            windowStartTime.put(userId, timestamp);
        }

        int numberOfExistingRequests = fixedWindow.get(userId);

        if (numberOfRequests <= numberOfExistingRequests) {
            return true;
        }
        fixedWindow.put(userId, numberOfExistingRequests + 1);

        return false;
    }
}
