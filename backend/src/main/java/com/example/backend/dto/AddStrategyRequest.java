package com.example.backend.dto;

import com.example.backend.model.Strategy;

public record AddStrategyRequest (
    long tokenId,
    Integer amount,
    Strategy.StrategyType type
){}
