package com.example.backend.dto;

import com.example.backend.model.Side;
import com.example.backend.model.Strategy;

public record NewDealRequest (
        String token,
        Strategy strategy,
        Side side
) {}
