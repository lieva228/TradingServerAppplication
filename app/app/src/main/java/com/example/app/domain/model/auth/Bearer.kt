package com.example.app.domain.model.auth

import com.google.gson.annotations.SerializedName

class Bearer {
    @SerializedName("bearer") var bearer: String? = null
}