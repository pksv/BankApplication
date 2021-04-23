package com.example.bankapp.Database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bankapp.Application;

public class LoginPreferences {
    private static final String PREFERENCE_NAME = "login_preference";
    private static LoginPreferences mInstance = null;

    public static synchronized LoginPreferences getInstance() {
        if (mInstance == null) {
            mInstance = new LoginPreferences();
        }
        return mInstance;
    }

    public String getUserId() {
        SharedPreferences preferences = Application.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(LoginPreferenceKey.USER_ID.getKey(), "");
    }

    public void setUserId(String userId) {
        SharedPreferences preferences = Application.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(LoginPreferenceKey.USER_ID.getKey(), userId).apply();
    }

    public String getName() {
        SharedPreferences preferences = Application.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(LoginPreferenceKey.NAME.getKey(), "");
    }

    public void setName(String userId) {
        SharedPreferences preferences = Application.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(LoginPreferenceKey.NAME.getKey(), userId).apply();
    }
}
