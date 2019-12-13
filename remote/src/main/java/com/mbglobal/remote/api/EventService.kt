package com.mbglobal.remote.api

import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.remote.dto.event.*
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface EventService {

    @GET("/api/event/eventdetail/{id}/")
    fun getEvent(@Path("id") eventId: Int): Single<EventDto>

    @POST("/api/event/events/")
    fun addEvent(@Body addEventDto: AddEventDto): Single<EventDto>

    @PUT("/api/event/eventdetail/{id}/")
    fun editEvent(@Path("id") eventId: Int, @Body addEventDto: AddEventDto): Single<EventDto>

    @GET("/api/event/eventsd/")
    fun getUserEvents(@Query("id") userId: Int): Single<List<EventDto>>

    @GET("/api/event/eventsd/")
    fun getUserCheckIns(@Query("id") userId: Int): Single<List<EventDto>>

    @GET("/api/event/eventsd/")
    fun getUserSuggestions(@Query("id") userId: Int): Single<List<EventDto>>

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8000/"
    }
}