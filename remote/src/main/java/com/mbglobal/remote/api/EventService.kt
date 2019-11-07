package com.mbglobal.remote.api

import com.mbglobal.remote.dto.event.EventDto
import com.mbglobal.remote.dto.event.EventGetDto
import com.mbglobal.remote.dto.event.EventResponseDto
import com.mbglobal.remote.dto.user.UserGetDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface EventService {

    @POST("/api/event/get_event")
    fun getEvent(@Body eventGetDto: EventGetDto) : Single<EventResponseDto>

    @POST("/api/event/add_event")
    fun addEvent(@Body eventDto: EventDto) : Completable

    @POST("/api/event/events")
    fun getUserEvents(@Body userId : String) : Single<List<String>>

    companion object{
        const val BASE_URL : String = "http://35.202.66.168:8000/"
    }
}