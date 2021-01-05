package com.mbglobal.data.repository

import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.LocationEntity
import io.reactivex.Single
import com.mbglobal.data.entity.event.S3ResponseEntity

object MockEventFactory {

    val imageList = listOf("https://upgradedpoints.com/wp-content/uploads/2018/06/Top-20-Amusement-Parks-in-North-America.jpg",
        "https://vignette.wikia.nocookie.net/friends/images/9/94/Central_Perk.jpg",
        "https://www.nickselway.com/images/xl/BEST.jpg",
        "https://cdn.rit.edu/images/news/2019-11/ICPC.jpg",
        "https://afremov.com/images/product/image_2347.jpeg",
        "https://i.pinimg.com/originals/a5/4a/5e/a54a5e8dbf0f5fc3e2753a1aebf852e5.jpg",
        "https://i2-prod.business-live.co.uk/enterprise/article16936655.ece/ALTERNATES/s1200/1_NWP_BEM_170919Muse_09.jpg")

    val EVENT1 = EventEntity(
        description = "This is a random mock event with a random image",
        endDate = "2019-11-11",
        startDate = "2019-10-10",
        eventOwner = 10,
        location = LocationEntity(
            12.0,
            13.0
        ),
        image = imageList[6],
        title = "Muse Concert",
        category = "Music",
        id = 12,
        startTime = "00:00",
        endTime = "00:00",
        owner = 20,
        checkinState = false,
        checkinCount = 27
    )

    val EVENT2 = EventEntity(
        description = "This is the place where friends reunion.",
        endDate = "2019-11-11",
        startDate = "2019-10-10",
        eventOwner = 10,
        location = LocationEntity(
            12.0,
            13.0
        ),
        image = imageList[1],
        title = "Central Perk",
        category = "TV",
        id = 12,
        startTime = "00:00",
        endTime = "00:00",
        owner = 20,
        checkinState = false,
        checkinCount = 7
    )

    val EVENT3 = EventEntity(
        description = "Backend is down. Mock is the answer!",
        endDate = "2019-11-11",
        startDate = "2019-10-10",
        eventOwner = 10,
        location = LocationEntity(
            12.0,
            13.0
        ),
        image = imageList[0],
        title = "Amusement Park",
        category = "Entertainment",
        id = 12,
        startTime = "00:00",
        endTime = "00:00",
        owner = 20,
        checkinState = false,
        checkinCount = 162
    )

    val EVENT4 = EventEntity(
        description = "Sorry but even this event is mocked!",
        endDate = "2019-11-11",
        startDate = "2019-10-10",
        eventOwner = 10,
        location = LocationEntity(
            12.0,
            13.0
        ),
        image = imageList[2],
        title = "Picasso Gallery",
        category = "Painting",
        id = 12,
        startTime = "00:00",
        endTime = "00:00",
        owner = 20,
        checkinState = false,
        checkinCount = 162
    )

    val EVENT5 = EventEntity(
        description = "Hire this guy, please!",
        endDate = "2019-11-11",
        startDate = "2019-10-10",
        eventOwner = 10,
        location = LocationEntity(
            12.0,
            13.0
        ),
        image = imageList[3],
        title = "Programming Contest",
        category = "IT",
        id = 12,
        startTime = "00:00",
        endTime = "00:00",
        owner = 20,
        checkinState = false,
        checkinCount = 162
    )

    val EVENT6 = EventEntity(
        description = "This show is about a developer looking for job",
        endDate = "2019-11-11",
        startDate = "2019-10-10",
        eventOwner = 10,
        location = LocationEntity(
            12.0,
            13.0
        ),
        image = imageList[4],
        title = "Theater of Hope",
        category = "Movies",
        id = 12,
        startTime = "00:00",
        endTime = "00:00",
        owner = 20,
        checkinState = false,
        checkinCount = 162
    )

    val EVENT7 = EventEntity(
        description = "This is a mock event, not the one you ordered! \n" +
                "Every functionality in this app is mocked due to server unavailability \n" +
                "Some parts of the app are implemented but couldn't be mocked such as search feature \n" +
                "",
        endDate = "2021-11-11",
        startDate = "2020-05-10",
        eventOwner = 10,
        location = LocationEntity(
            19.0,
            22.0
        ),
        image = imageList[5],
        title = "Job Interview",
        category = "Development",
        id = 12,
        startTime = "11:00",
        endTime = "23:30",
        owner = 20,
        checkinState = false,
        checkinCount = 162
    )

    val events = listOf(EVENT1, EVENT5, EVENT6, EVENT4, EVENT2, EVENT3)

    fun getEvents() : Single<List<EventEntity>>{
        return Single.fromCallable { events }
    }

    fun getEvent() : Single<EventEntity>{
        return Single.fromCallable { EVENT7 }
    }

}