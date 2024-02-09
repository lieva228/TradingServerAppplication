package com.example.backend.bybit.request.data;

import lombok.Getter;

@Getter
public enum TriggerBy {
    LAST_PRICE("LastPrice"),
    INDEX_PRICE("IndexPrice"),
    MARK_PRICE("MarkPrice");

    private final String trigger;

    TriggerBy(String trigger) {
        this.trigger = trigger;
    }
}