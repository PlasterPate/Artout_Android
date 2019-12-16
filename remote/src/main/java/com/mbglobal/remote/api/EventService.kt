package com.mbglobal.remote.api

import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.remote.dto.event.*
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface EventService {

    @GET("/api/v1.0/events/{id}/")
    fun getEvent(@Path("id") eventId: Int): Single<EventDto>

    @PUT("/api/v1.0/events/{id}/")
    fun editEvent(@Path("id") eventId: Int, @Body addEventDto: AddEventDto): Single<EventDto>

    @POST("/api/v1.0/events/")
    fun addEvent(@Body addEventDto: AddEventDto): Single<EventDto>

    @GET("/api/v1.0/events/")
    fun getUserEvents(): Single<List<EventDto>>

    @GET("/api/v1.0/events/")
    fun getUserCheckIns(): Single<List<EventDto>>

    @GET("/api/v1.0/events/")
    fun getUserSuggestions(): Single<List<EventDto>>

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8080/"
    }
}