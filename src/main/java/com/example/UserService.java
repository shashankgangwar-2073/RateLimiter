package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    public static Map<String, User> userData = new ConcurrentHashMap<>();

    public UserService() {
    }

    public void addUser(String userId, UserType type) {
        User user = new User(type, userId);
        userData.put(userId, user);
    }
}
