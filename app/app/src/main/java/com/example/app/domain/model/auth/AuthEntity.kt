package com.example.app.domain.model.auth

import com.google.gson.annotations.SerializedName

data class AuthEntity(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
)