package com.mbglobal.remote.dto.user

import com.google.gson.annotations.SerializedName

data class UserGetDto(
    @SerializedName("user")
    val username : String
)