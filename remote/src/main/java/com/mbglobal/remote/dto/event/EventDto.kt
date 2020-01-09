package com.mbglobal.remote.dto.event


import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("checkin_count")
    val checkinCount: Int?,
    @SerializedName("description")
    val description: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_checked_in")
    val isCheckedIn: Boolean?,
    @SerializedName("location")
    val location: LocationDto?,
    @SerializedName("owner")
    val owner: Int,
    @SerializedName("picture_url")
    val pictureUrl: String?,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("title")
    val title: String
)