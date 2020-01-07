package com.mbglobal.remote.dto

import com.google.gson.annotations.SerializedName
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.user.UserEntity

data class CheckinDto(
    @SerializedName("user")
    val userEntity: UserEntity,
    @SerializedName("event")
    val eventEntity: EventEntity,
    @SerializedName("go_time")
    val goTime: String,
    @SerializedName("submitted_time")
    val submittedTime: String
)