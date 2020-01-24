package com.mbglobal.data.repository

import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.LocationEntity

object MockEventFactory {

    val COLDPLAY_CONCERT = EventEntity(
        description = "This is a coldplay concert",
        endDate = "2019-11-11",
        startDate = "2019-10-10",
        eventOwner = 10,
        location = LocationEntity(
            12.0,
            13.0
        ),
        image = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        title = "Coldplay Concert",
        category = "Music",
        id = 12,
        owner = 20,
        startTime = "00:00",
        endTime = "00:00"
    )

    val COLDPLAY_CONCERT2 = EventEntity(
        description = "This is a coldplay concert",
        endDate = "2019-11-11",
        startDate = "2019-10-10",
        eventOwner = 10,
        location = LocationEntity(
            12.0,
            13.0
        ),
        image = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        title = "Coldplay Concert 2",
        category = "Music",
        id = 12,
        owner = 20,
        startTime = "00:00",
        endTime = "00:00"
    )

    val COLDPLAY_CONCERT3 = EventEntity(
        description = "This is a coldplay concert",
        endDate = "2019-11-11",
        startDate = "2019-10-10",
        eventOwner = 10,
        location = LocationEntity(
            12.0,
            13.0
        ),
        image = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg",
        title = "Coldplay Concert 3",
        category = "Music",
        id = 12,
        owner = 20,
        startTime = "00:00",
        endTime = "00:00"
    )


}