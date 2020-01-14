package com.mbglobal.remote.dto.checkin

import com.google.gson.annotations.SerializedName
import com.mbglobal.remote.dto.event.EventDto

data class AddCheckinDto(
    @SerializedName("event_id")
    val eventId: String
)