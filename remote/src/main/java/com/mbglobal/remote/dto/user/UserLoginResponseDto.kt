package com.mbglobal.remote.dto.user

import com.google.gson.annotations.SerializedName

data class UserLoginResponseDto(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String,
    @SerializedName("username")
    val id: String
)