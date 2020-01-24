package com.mbglobal.remote.dto.event


import com.google.gson.annotations.SerializedName

data class AddEventDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("end_date")
    val endDateTime: String,
    @SerializedName("location")
    val location: LocationDto?,
    @SerializedName("picture_url")
    val image: String?,
    @SerializedName("start_date")
    val startDateTime: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("owner")
    val owner: Int
)