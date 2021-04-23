package com.example.bankapp.Database;

public enum LoginPreferenceKey {
    USER_ID("user_id"), NAME("name");
    private final String key;

    LoginPreferenceKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
