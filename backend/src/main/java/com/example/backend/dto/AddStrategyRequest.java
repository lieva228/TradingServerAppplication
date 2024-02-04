package com.example.backend.dto;

import com.example.backend.model.Strategy;

public record AddStrategyRequest (
    long userId,
    long tokenId,
    double amount,
    Strategy.StrategyType type
){}
