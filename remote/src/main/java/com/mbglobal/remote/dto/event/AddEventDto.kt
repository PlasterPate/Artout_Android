package com.mbglobal.remote.dto.event


import com.google.gson.annotations.SerializedName

data class AddEventDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("location")
    val location: LocationDto,
    @SerializedName("picture_url")
    val image: String?,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val category: String
)