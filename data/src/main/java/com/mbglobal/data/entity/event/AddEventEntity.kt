package com.mbglobal.data.entity.event

data class AddEventEntity(
    val description: String,
    val endDate: String,
    val eventOwner: Int,
    val location: LocationEntity,
    val image: String?,
    val startDate: String,
    val title: String,
    val category: String
)