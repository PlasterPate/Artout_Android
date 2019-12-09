package com.mbglobal.remote.api

import com.mbglobal.remote.dto.user.*
import io.reactivex.Single
import retrofit2.http.*

interface UserService {

    @POST("/api/register/")
    fun register(@Body userRegisterItemDto: UserRegisterItemDto): Single<UserRegisterResponseDto>

    @POST("/api/login/")
    fun login(@Body userLoginItemDto: UserLoginItemDto): Single<UserLoginResponseDto>

    @POST("/api/users/")
    fun getUser(@Body userGetDto: UserGetDto, @Header("Authorization") bearerToken: String): Single<UserResponseDto>

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8000/"
    }

}