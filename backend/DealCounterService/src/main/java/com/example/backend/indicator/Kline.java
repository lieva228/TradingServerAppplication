package com.example.backend.indicator;

public record Kline(
        long time,
        double open,
        double high,
        double low,
        double close,
        double volume,
        double turnover
){}