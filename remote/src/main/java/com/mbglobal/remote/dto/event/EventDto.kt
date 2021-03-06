package com.mbglobal.remote.dto.event


import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("start_date")
    val startDateTime: String,
    @SerializedName("end_date")
    val endDateTime: String,
    @SerializedName("location")
    val location: LocationDto?,
    @SerializedName("owner")
    val owner: Int,
    @SerializedName("is_checked_in")
    val isCheckedIn: Boolean?,
    @SerializedName("checkin_count")
    val checkinCount: Int?,
    @SerializedName("picture_url")
    val pictureUrl: String
)