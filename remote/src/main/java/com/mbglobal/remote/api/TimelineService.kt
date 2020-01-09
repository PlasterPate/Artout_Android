package com.mbglobal.remote.api

import com.mbglobal.remote.dto.event.EventDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TimelineService {

    @GET("/api/v1.0/events/timeline/")
    fun getTimelineItems(@Query("page") pageNumber: Int = 1, @Query("page_size") pageSize: Int = 10): Single<List<EventDto>>

    companion object {
        const val BASE_URL = "http://35.202.66.168:8080/"
    }
}