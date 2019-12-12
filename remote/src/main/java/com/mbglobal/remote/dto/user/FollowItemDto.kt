package com.mbglobal.remote.dto.user

import com.google.gson.annotations.SerializedName

data class FollowItemDto (
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar")
    val avatar: String
)