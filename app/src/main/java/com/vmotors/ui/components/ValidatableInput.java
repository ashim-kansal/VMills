package com.vmotors.ui.components;

public interface ValidatableInput<T> {
    T getValue();
    void clearErrors();
    void showError(String message);
}