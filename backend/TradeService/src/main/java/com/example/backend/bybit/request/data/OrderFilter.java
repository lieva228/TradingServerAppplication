package com.example.backend.bybit.request.data;

import lombok.Getter;

@Getter
public enum OrderFilter {
    ORDER("Order"),
    TPSL_ORDER("tpslOrder"),
    STOP_ORDER("StopOrder");

    private final String orderFilterType;

    OrderFilter(String orderFilterType) {
        this.orderFilterType = orderFilterType;
    }
}