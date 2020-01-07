package com.mbglobal.remote.dto.checkin

import com.google.gson.annotations.SerializedName
import com.mbglobal.remote.dto.event.EventDto

data class AddCheckinDto(
    @SerializedName("event")
    val eventDto: EventDto,
    @SerializedName("go_time")
    val goTime: String
)