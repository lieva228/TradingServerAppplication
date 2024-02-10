package com.example.backend.model;

import com.example.backend.exception.ResourceNotFoundException;
import jakarta.persistence.*;
import lombok.*;

public enum Strategy {
    RSI("Rsi"),
    SMA("Sma"),
    NONE("None");

    private final String stringValue;

    Strategy(String stringValue) {
        this.stringValue = stringValue;
    }

    public static Strategy fromString(String value) {
        for (Strategy enumValue : Strategy.values()) {
            if (enumValue.stringValue.equalsIgnoreCase(value)) {
                return enumValue;
            }
        }
        return NONE;
    }
}