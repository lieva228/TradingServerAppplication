package com.example.backend.dto;

public record UserEditRequest (
    String username,
    String apiKey,
    String secretKey
) {
}