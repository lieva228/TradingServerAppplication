package com.example.backend.dto;


public record SignInRequest (
    String username,
    String password
) {}
