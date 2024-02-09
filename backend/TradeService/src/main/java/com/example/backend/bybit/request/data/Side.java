package com.example.backend.bybit.request.data;

import lombok.Getter;

@Getter
public enum Side {
    BUY("Buy"),
    SELL("Sell");

    private final String transactionSide;

    Side(String transactionSide) {
        this.transactionSide = transactionSide;
    }
}
