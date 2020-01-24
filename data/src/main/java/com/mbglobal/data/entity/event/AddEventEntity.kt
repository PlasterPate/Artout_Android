package com.mbglobal.data.entity.event

data class AddEventEntity(
    val title: String,
    val description: String,
    val owner: Int,
    val location: LocationEntity,
    val image: String?,
    val startDate: String,
    val startTime: String,
    val endDate: String,
    val endTime: String,
    val category: String
)