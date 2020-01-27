package com.mbglobal.remote.api

import com.mbglobal.remote.dto.checkin.AddCheckinDto
import com.mbglobal.remote.dto.checkin.CheckinDto
import com.mbglobal.remote.dto.event.*
import com.mbglobal.remote.dto.user.UserDto
import com.mbglobal.remote.dto.user.UserProfileDto
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface EventService {

    @GET("/api/v1.0/events/{id}/")
    fun getEvent(@Path("id") eventId: Int): Single<EventDto>

    @PUT("/api/v1.0/events/{id}/")
    fun editEvent(@Path("id") eventId: Int, @Body addEventDto: AddEventDto): Single<EventDto>

    @POST("/api/v1.0/events/")
    fun addEvent(@Body addEventDto: AddEventDto): Single<AddEventResponseDto>

    @GET("/api/v1.0/events/")
    fun getUserEvents(@Query("owner") userId: String): Single<List<EventDto>>

    @GET("/api/v1.0/checkins/")
    fun getUserCheckIns(@Query("user") userId: String): Single<List<CheckinDto>>

    @POST("api/v1.0/checkins/")
    fun checkin(@Body addCheckinDto: AddCheckinDto): Single<ResponseBody>

    @DELETE("api/v1.0/checkins/{id}/")
    fun checkout(@Path("id") eventId: String): Single<ResponseBody>

    @GET("/api/v1.0/events/")
    fun searchEvent(@QueryMap query: Map<String,String>): Single<List<EventDto>>

    @GET("/api/v1.0/users/profile/")
    fun getUserProfile(@Query("user") userId: String?): Single<UserProfileDto>

    @GET("/api/v1.0/users/")
    fun searchUser(@Query("search") query: String): Single<List<UserDto>>

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8080/"
    }
}