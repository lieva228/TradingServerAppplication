package com.example.app.domain.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class RegistrationDto(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("username") var username: String,
    @SerializedName("apiKey") var apiKey: String,
    @SerializedName("secretKey") var secretKey: String
)