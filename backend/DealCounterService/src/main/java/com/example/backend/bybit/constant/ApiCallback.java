package com.example.backend.bybit.constant;

public interface ApiCallback<T> {
    void onResponse(T response);
    default void onFailure(Throwable cause) {}
}
