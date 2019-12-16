package com.mbglobal.remote.api

import com.mbglobal.remote.dto.user.UserDto
import com.mbglobal.remote.dto.user.FollowRequestDto
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface FollowerService {

    @GET("/api/v1.0/follow/followers/")
    fun getUserFollowers(): Single<List<UserDto>>

    @GET("/api/v1.0/follow/users/{id}/followers")
    fun getUserFollowers(@Path("id") userId: String): Single<List<UserDto>>

    @GET("/api/v1.0/follow/followers/{id}/")
    fun getFollower(@Path("id") userId: String): Single<UserDto>

    @DELETE("/api/v1.0/follow/followers/{id}/")
    fun removeFollower(@Path("id") userId: String): Single<ResponseBody>

    @GET("/api/v1.0/follow/followers/requests/")
    fun getFollowRequests(): Single<List<UserDto>>

    @PUT("/api/v1.0/follow/followers/requests/{id}/")
    fun acceptRequest(@Path("id") userId: String): Single<ResponseBody>

    @DELETE("/api/v1.0/follow/followers/requests/{id}/")
    fun rejectRequest(@Path("id") userId: String): Single<ResponseBody>

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8080/"
    }
}