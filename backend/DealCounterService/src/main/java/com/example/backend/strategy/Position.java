package com.example.backend.strategy;

import lombok.Getter;

@Getter
public enum Position {
    LONG("Long"),
    NONE("None"),
    SHORT("Short");

    private final String position;

    Position(String position) {
        this.position = position;
    }
}
