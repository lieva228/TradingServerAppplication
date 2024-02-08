package com.example.app.domain.repository

import com.example.app.domain.model.Token

interface TokenRepository {
    suspend fun getAllTokens(): List<Token>
}