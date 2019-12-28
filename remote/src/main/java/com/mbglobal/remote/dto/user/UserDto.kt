package com.mbglobal.remote.dto.user

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("state")
    val state: Int
)