package com.example.backend.strategy;

import lombok.Getter;

@Getter
public enum Condition {
    LONG_STOP("Long stop"),
    READY("Ready"),
    OPEN("Open"),
    SHORT_STOP("Short stop");

    private final String condition;

    Condition(String condition) {
        this.condition = condition;
    }
}
