package com.example;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter implements RateLimitingStrategy {

    Map<String, Queue<Integer>> windowMap;

    public SlidingWindowRateLimiter() {
        windowMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isRateLimited(String userId, int timestamp, int numberOfRequests, int window) {

        if (!windowMap.containsKey(userId)) {
            Queue<Integer> q = new ArrayBlockingQueue<>(11);
            windowMap.put(userId, q);
        }

        Queue<Integer> currQueue = windowMap.get(userId);

        while (!currQueue.isEmpty() && currQueue.peek() <= timestamp - window) {
            currQueue.poll();
        }

        if (currQueue.size() >= numberOfRequests) {
            return true;
        }
        currQueue.offer(timestamp);
        return false;
    }
}
