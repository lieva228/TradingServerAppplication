package com.example.backend.bybit.request.data;

import lombok.Getter;

/**
 * SMP execution type
 */
@Getter
public enum SmpType {
    NONE("None"),
    CANCEL_MAKER("CancelMaker"),
    CANCEL_TAKER("CancelTaker"),
    CANCEL_BOTH("CancelBoth");

    private final String description;

    SmpType(String description) {
        this.description = description;
    }
}