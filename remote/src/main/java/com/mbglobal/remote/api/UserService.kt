package com.mbglobal.remote.api

import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.remote.dto.user.*
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserService {

    @POST("/api/register/")
    fun register(@Body userRegisterItemDto: UserRegisterItemDto): Single<UserRegisterResponseDto>

    @POST("/api/login/")
    fun login(@Body userLoginItemDto: UserLoginItemDto): Single<UserLoginResponseDto>

    @POST("/api/users/")
    fun getUser(@Body userGetDto: UserGetDto, @Header("Authorization") bearerToken: String): Single<UserResponseDto>

    //////////////

    @PUT("/api/.../")
    fun follow(username: String): Completable

    @GET("/api/.../")
    fun getFollowers(userId: Int): Single<List<UserEntity>>

    @GET("/api/.../")
    fun getFollowings(userId: Int): Single<List<UserEntity>>

    @GET("/api/.../")
    fun getFollowRequests(userId: Int): Single<List<UserEntity>>?


    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8000/"
    }

}