package com.example.backend.model;

import lombok.Getter;

@Getter
public enum Side {
    BUY("Buy"),
    SELL("Sell"),
    NONE("None");

    private final String transactionSide;

    Side(String transactionSide) {
        this.transactionSide = transactionSide;
    }

    public static Side fromString(String value) {
        for (Side enumValue : Side.values()) {
            if (enumValue.transactionSide.equalsIgnoreCase(value)) {
                return enumValue;
            }
        }
        return NONE;
    }
}
