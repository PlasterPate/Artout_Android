package com.mbglobal.data.entity.event

data class EventEntity(
    val id: Int,
    val title: String,
    val description: String,
    val eventOwner: Int,
    val location: LocationEntity?,
    val image: String?,
    val startDate: String,
    val startTime: String,
    val endDate: String,
    val endTime: String,
    val category: String,
    val owner: Int
)