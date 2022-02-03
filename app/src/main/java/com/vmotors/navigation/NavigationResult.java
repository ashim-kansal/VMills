package com.vmotors.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NavigationResult {

    private final Map<String, Object> params = new HashMap<>();

    private boolean isOk = false;
    private int resultCode;

    public NavigationResult(final boolean isOK, final Map<String, Object> params) {
        this.isOk = isOK;
        this.params.putAll(params);
    }

    public NavigationResult(final int resultCode, final Intent data) {
        isOk = resultCode == Activity.RESULT_OK;
        this.resultCode = resultCode;

        if (data != null) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                final Set<String> keys = extras.keySet();
                for (String key : keys) {
                    final Object value = extras.get(key);
                    params.put(key, value);
                }
            }
        }
    }

    public NavigationResult() {

    }

    public boolean isOk() {
        return isOk;
    }

    public String getStringParam(final String name) {
        return getTypedValue(name, String.class);
    }

    public Long getLongParam(final String name) {
        return getTypedValue(name, Long.class);
    }

    public Integer getIntParam(final String name) {
        return getTypedValue(name, Integer.class);
    }

    public Boolean getBooleanParam(final String name) {
        return getTypedValue(name, Boolean.class);
    }

    public Parcelable getParcelableParam(final String name) {
        return getTypedValue(name, Parcelable.class);
    }

    private <T> T getTypedValue(String key, Class<T> tClass) {
        Object value = params.get(key);
        if (value == null) {
            return null;
        }
        if (tClass.isAssignableFrom(value.getClass())) {
            return (T) value;
        }
        return null;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public int getResultCode() {
        return resultCode;
    }
}
