package com.mbglobal.local.mappers

import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.LocationEntity
import com.mbglobal.data.entity.event.S3ResponseEntity
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
        locationLat = location?.latitude,
        locationLong = location?.longitude,
        checkinState = checkinState,
        checkinCount = checkinCount
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
        startTime = startDate,
        endDate = endDate,
        endTime = endDate,
        location = LocationEntity(locationLong?:0.0, locationLat?:0.0),
        owner = 0,
        checkinCount = checkinCount,
        checkinState = checkinState
    )
}