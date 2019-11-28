package com.mbglobal.remote.dto.event

import com.google.gson.annotations.SerializedName

data class EventResponseDto (
    @SerializedName("slug")
    val slug : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("image")
    val images : String?,
    @SerializedName("description")
    val description : String,
    @SerializedName("startDate")
    val startDate : String,
    @SerializedName("endDate")
    val endDate : String,
    @SerializedName("startTime")
    val startTime : String,
    @SerializedName("endTime")
    val endTime : String,
    @SerializedName("rate")
    val rate : Double,
    @SerializedName("category")
    val category : String
)