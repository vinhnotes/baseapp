package com.vtu.baseapp.common;

public interface Callback<T> {
    void call(T... object);
}
