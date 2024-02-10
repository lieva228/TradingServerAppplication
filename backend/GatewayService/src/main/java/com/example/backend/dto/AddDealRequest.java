package com.example.backend.dto;

import com.example.backend.model.Strategy;

public record AddDealRequest(
    String token,
    Integer amount,
    Strategy type
){}
