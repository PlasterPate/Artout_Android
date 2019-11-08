package com.mbglobal.remote.dto.event


import com.google.gson.annotations.SerializedName

class LocationDto(
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("latitude")
    val latitude: Double
)