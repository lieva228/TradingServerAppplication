package com.example.app.domain.model.auth

import com.google.gson.annotations.SerializedName

data class RegistrationEntity(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("username") var username: String,
    @SerializedName("apiKey") var apiKey: String,
    @SerializedName("secretKey") var secretKey: String
)