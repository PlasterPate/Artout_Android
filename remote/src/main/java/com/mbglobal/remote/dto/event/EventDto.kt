package com.mbglobal.remote.dto.event


import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("owner")
    val owner: Int,
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