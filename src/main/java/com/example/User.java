package com.example;

public class User {
    UserType type;
    String userId;

    public User(UserType type, String userId) {
        this.type = type;
        this.userId = userId;
    }

    public UserType getType() {
        return this.type;
    }
}
