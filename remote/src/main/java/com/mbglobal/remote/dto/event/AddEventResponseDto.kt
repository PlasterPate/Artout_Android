package com.mbglobal.remote.dto.event

import com.google.gson.annotations.SerializedName

data class AddEventResponseDto (
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
    @SerializedName("s3_response")
    val s3ResponseDto: S3ResponseDto
)