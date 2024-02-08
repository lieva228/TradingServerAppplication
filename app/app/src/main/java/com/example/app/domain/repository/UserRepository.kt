package com.example.app.domain.repository

import com.example.app.domain.model.Strategy
import com.example.app.domain.model.User

interface UserRepository {

    suspend fun getInfo(): User

    suspend fun getStrategies(): List<Strategy>

    suspend fun addStrategy(): Strategy

    suspend fun edit(): User
}