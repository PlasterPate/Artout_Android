package com.mbglobal.remote.dto.user

import com.google.gson.annotations.SerializedName
import com.mbglobal.data.entity.user.UserEntity

data class FollowRequestDto(
    @SerializedName("source")
    val source: UserEntity,
    @SerializedName("destination")
    val destination: UserEntity,
    @SerializedName("id")
    val id: Int
)