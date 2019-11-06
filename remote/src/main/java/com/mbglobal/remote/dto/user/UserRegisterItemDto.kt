package com.mbglobal.remote.dto.user

import com.google.gson.annotations.SerializedName

data class UserRegisterItemDto(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)