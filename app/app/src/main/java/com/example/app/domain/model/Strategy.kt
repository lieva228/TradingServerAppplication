package com.example.app.domain.model

import com.google.gson.annotations.SerializedName

data class Strategy (
    @SerializedName("amount") var amount: Double,
    @SerializedName("strategy") var strategy: String
)