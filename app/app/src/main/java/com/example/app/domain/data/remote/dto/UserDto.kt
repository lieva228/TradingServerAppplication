package com.example.app.domain.data.remote.dto

import com.google.gson.annotations.SerializedName


data class UserDto (
    @SerializedName("id")
    var id: Int?,

    @SerializedName("username")
    var username: String?,

    @SerializedName("password")
    var password: String?,

    @SerializedName("email")
    var email: String?,

    @SerializedName("apiKey")
    var apiKey: String?,

    @SerializedName("secretKey")
    var secretKey: String?,

    @SerializedName("role")
    var role: String?,

    @SerializedName("strategies")
    var strategies: List<Any>?,

    @SerializedName("enabled")
    var enabled: Boolean?,

    @SerializedName("authorities")
    var authorities: List<AuthorityDto>?,

    @SerializedName("accountNonLocked")
    var accountNonLocked: Boolean?,

    @SerializedName("accountNonExpired")
    var accountNonExpired: Boolean?,

    @SerializedName("credentialsNonExpired")
    var credentialsNonExpired: Boolean?
)