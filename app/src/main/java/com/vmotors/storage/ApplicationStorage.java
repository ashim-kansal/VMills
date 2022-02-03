package com.vmotors.storage;


public interface ApplicationStorage {

    void putBoolean(String key, boolean value);

    void putInteger(String key, int value);

    void putFloat(String key, float value);

    void putLong(String key, long value);

    void putString(String key, String value);

    void putObject(String key, Object value);

    boolean getBoolean(String key, boolean defaultValue);

    int getInteger(String key, int defaultValue);

    float getFloat(String key, float defaultValue);

    String getString(String key, String defaultValue);

    long getLong(String key, long defaultValue);

    Object getObject(String key);

    void clearValue(String key);

}
