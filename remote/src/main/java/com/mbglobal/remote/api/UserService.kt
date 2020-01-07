package com.mbglobal.remote.api

import com.mbglobal.remote.dto.checkin.CheckinDto
import com.mbglobal.remote.dto.user.*
import io.reactivex.Single
import retrofit2.http.*

interface UserService {

    @POST("/api/v1.0/auth/register/")
    fun register(@Body userRegisterItemDto: UserRegisterItemDto): Single<UserRegisterResponseDto>

    @POST("/api/v1.0/auth/login/")
    fun login(@Body userLoginItemDto: UserLoginItemDto): Single<UserLoginResponseDto>

    @GET("api/v1.0/chekins/")
    fun getEventCheckins(@Query("event") eventId: String): Single<List<CheckinDto>>

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8080/"
    }

}