package com.example.app.domain.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthorityDto (
    @SerializedName("authority")
    private var authority: String
)

