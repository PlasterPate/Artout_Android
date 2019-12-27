package com.mbglobal.remote.api

import com.google.gson.annotations.SerializedName
import com.mbglobal.remote.dto.user.UserDto
import com.mbglobal.remote.dto.user.FollowRequestDto
import com.mbglobal.remote.dto.user.UserGetDto
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface FollowingService {

    @GET("/api/v1.0/follow/followings/")
    fun getUserFollowings(): Single<List<UserDto>>

    @GET("/api/v1.0/follow/users/{id}/followings")
    fun getUserFollowings(@Path("id") userId: String): Single<List<UserDto>>

    @GET("/api/v1.0/follow/followings/{id}/")
    fun getFollowing(@Path("id") userId: String): Single<UserDto>

    @DELETE("/api/v1.0/follow/followings/{id}/")
    fun unfollow(@Path("id") userId: String): Single<ResponseBody>

    @POST("/api/v1.0/follow/followings/pendings/")
    fun follow(@Body userGetDto: UserGetDto): Single<ResponseBody>

    @GET("/api/v1.0/follow/followings/pendings/")
    fun getFollowPendings(): Single<List<UserDto>>

    @DELETE("/api/v1.0/follow/followings/pendings/{id}/")
    fun cancelFollowPending(@Path("id") userId: String): Completable

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8080/"
    }
}