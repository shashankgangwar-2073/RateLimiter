package com.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }
        UserService userService = new UserService();
        userService.addUser("User1", UserType.PRO);
        userService.addUser("User2", UserType.FREE);

        RateLimiter rateLimiter = new RateLimiter(2, 2);

        // Sliding window
        rateLimiter.isRateLimited("User1", 1);
        rateLimiter.isRateLimited("User1", 2);
        rateLimiter.isRateLimited("User1", 3);
        rateLimiter.isRateLimited("User1", 3);

        // Fixed window
        rateLimiter.isRateLimited("User2", 1);
        rateLimiter.isRateLimited("User2", 2);
        rateLimiter.isRateLimited("User2", 3);
        rateLimiter.isRateLimited("User2", 4);
        rateLimiter.isRateLimited("User2", 4);
    }
}
