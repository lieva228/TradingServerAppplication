package com.example.backend.dto;

public record UserEditRequest (
    String apiKey,
    String secretKey
) {
}