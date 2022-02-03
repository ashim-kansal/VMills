package com.vmotors.storage;

import android.content.SharedPreferences;


public class PreferencesApplicationStorage implements ApplicationStorage {

    private static final String TAG = "PreferencesAppStorage";

    private final SharedPreferences sharedPreferences;

    public PreferencesApplicationStorage(final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void putBoolean(final String key, final boolean value) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    @Override
    public void putInteger(final String key, final int value) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    @Override
    public void putFloat(final String key, final float value) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    @Override
    public void putLong(String key, long value) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    @Override
    public void putString(final String key, final String value) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public void putObject(final String key, final Object value) {
    }

    @Override
    public boolean getBoolean(final String key, final boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public String getString(final String key, final String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    @Override
    public int getInteger(final String key, final int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    @Override
    public float getFloat(final String key, final float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    @Override
    public Object getObject(final String key) {
        return null;
    }

    @Override
    public void clearValue(final String key) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
}