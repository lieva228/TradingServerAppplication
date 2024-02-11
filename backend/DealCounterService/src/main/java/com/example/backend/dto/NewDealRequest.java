package com.example.backend.dto;

import com.example.backend.model.Side;

public record NewDealRequest (
        String token,
        String strategy,
        Side side
) {}
