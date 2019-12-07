package com.mbglobal.remote.dto.user

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("avatar")
    val firstName: String,
    @SerializedName("avatar")
    val id: Int,
    @SerializedName("avatar")
    val lastName: String,
    @SerializedName("avatar")
    val username: String
)