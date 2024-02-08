package com.example.app.domain.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
)