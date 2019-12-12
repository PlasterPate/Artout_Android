package com.mbglobal.remote.api

import com.mbglobal.remote.dto.user.UserDto
import com.mbglobal.remote.dto.user.FollowRequestDto
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface FollowingService {

    @GET("followings/")
    fun getUserFollowings(): Single<List<UserDto>>

    @GET("users/{id}/followings")
    fun getUserFollowings(@Path("id") userId: String): Single<List<UserDto>>

    @GET("followings/{id}/")
    fun getFollowing(@Path("id") userId: String): Single<UserDto>

    @DELETE("followings/{id}/")
    fun unfollow(@Path("id") userId: String): Single<ResponseBody>

    @POST("followings/pendings/")
    fun follow(@Body userId: String): Single<ResponseBody>

    @GET("followings/pendings/")
    fun getFollowPendings(): Single<List<FollowRequestDto>>

    @DELETE("followings/pendings/{id}/")
    fun cancelFollowPending(@Path("id") userId: String): Single<ResponseBody>

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8000/"
    }
}