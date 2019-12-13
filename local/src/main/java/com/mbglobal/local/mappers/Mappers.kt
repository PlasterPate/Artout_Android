package com.mbglobal.local.mappers

import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.LocationEntity
import com.mbglobal.local.database.tables.EventTable

fun EventEntity.toEventTable(): EventTable {
    return EventTable(
        id = id,
        title = title,
        imagePath = image,
        description = description,
        category = category,
        eventOwner = eventOwner,
        startDate = startDate,
        endDate = endDate,
        locationLat = location.latitude,
        locationLong = location.longitude
    )
}

fun EventTable.toEventEntity(): EventEntity {
    return EventEntity(
        id = id,
        title = title,
        image = imagePath,
        description = description,
        category = category,
        eventOwner = eventOwner,
        startDate = startDate,
        endDate = endDate,
        location = LocationEntity(locationLong, locationLat)
    )
}