package com.mbglobal.remote.dto.user

import com.google.gson.annotations.SerializedName

data class UserRegisterResponseDto(
    @SerializedName("id")
    val id : String
)