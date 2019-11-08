package com.mbglobal.remote.api

import com.mbglobal.remote.dto.event.*
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface EventService {

    @POST("/api/event/eventdetail/{id}")
    fun getEvent(@Path("id") eventId: Int) : Single<EventDto>

    @POST("/api/event/events/")
    fun addEvent(@Body addEventDto: AddEventDto) : Single<EventDto>

    @GET("/api/event/events")
    fun getUserEvents(@Query("id") userId : Int) : Single<List<String>>

    companion object{
        const val BASE_URL : String = "http://35.202.66.168:8000/"
    }
}

//fun main() {
//
//    val service = Retrofit.Builder()
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(TokenService.BASE_URL)
//        .build()
//        .create(EventService::class.java)
//
//    val location = LocationDto(
//        12.0,
//        10.0
//    )
//
//    val event = AddEventDto(
//        "This is the event description",
//        "2019-01-01",
//        1,
//        location,
//        "http://www.com",
//        "2020-01-02",
//        "Title",
//        "Concerts"
//    )
//
//}