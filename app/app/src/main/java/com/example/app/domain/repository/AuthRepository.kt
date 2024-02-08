package com.example.app.domain.repository

import com.example.app.domain.model.auth.AuthEntity
import com.example.app.domain.model.auth.RegistrationEntity

interface AuthRepository {

    suspend fun signUp(
        username: String,
        email: String,
        password: String,
        apiKey: String,
        secretKey: String
    ): AuthEntity

    suspend fun signIn(username: String, password: String): RegistrationEntity
}