package com.mbglobal.remote.api

import com.mbglobal.remote.dto.user.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("/api/register/")
    fun register(@Body userRegisterItemDto: UserRegisterItemDto) : Single<UserRegisterResponseDto>

    @POST("/api/login/")
    fun login(@Body userLoginItemDto: UserLoginItemDto) : Single<UserLoginResponseDto>

    @POST("/api/users/")
    fun getUser(@Body userGetDto : UserGetDto, @Header("Authorization") bearerToken: String) : Single<UserResponseDto>

    companion object {
        const val BASE_URL : String = "http://194.225.229.210:9000/api/"
    }

}