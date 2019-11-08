package com.mbglobal.data.entity.user

data class EventEntity(
    val slug : String,
    val title : String,
    val image : String?,
    val description : String,
    val startDate : String,
    val endDate : String,
    val startTime : String,
    val endTime : String,
    val rate : Double,
    val category : String
)