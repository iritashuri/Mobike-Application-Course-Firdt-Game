package com.example.fistassigment;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySPV {


    public interface KEYS {
//        public static final String USER_DATA = "USER_DATA";
//        public static final String USER_LAST_LOGIN_TIME_STAMP = "USER_LAST_LOGIN_TIME_STAMP";
//        public static final String USER_MAIL = "USER_MAIL";
//        public static final String MOVIE_NAME = "MOVIE_NAME";
        public static final String CURRENT_WINNER = "CURRENT_WINNER";
    }

    private SharedPreferences prefs;

    public MySPV(Context context) {
        prefs = context.getSharedPreferences("MY_SP", MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public boolean getBoolean(String key, boolean def) {
        return prefs.getBoolean(key, def);
    }

    public int getInt(String key, int def) {
        return prefs.getInt(key, def);
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public float getFloat(String key, float def) {
        return prefs.getFloat(key, def);
    }

    public void putfloat(String key, float value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(key, value);
        editor.apply();
    }


    public float getLong(String key, long def) {
        return prefs.getLong(key, def);
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.apply();
    }

}