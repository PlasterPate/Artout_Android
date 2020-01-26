package com.mbglobal.remote.dto.checkin

import com.google.gson.annotations.SerializedName
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.remote.dto.event.EventDto
import com.mbglobal.remote.dto.user.UserDto

data class CheckinDto(
    @SerializedName("user")
    val userEntity: UserDto,
    @SerializedName("event")
    val eventEntity: EventDto,
    @SerializedName("go_time")
    val goTime: String,
    @SerializedName("submitted_time")
    val submittedTime: String
)