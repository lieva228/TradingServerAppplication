package com.example.backend.model;

public record Deal (
    Strategy strategy,
    Integer amount,
    String token
) {}
