package com.example.app.domain.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

class BearerDto {
    @SerializedName("bearer") var bearer: String? = null
}