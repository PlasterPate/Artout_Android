package com.mbglobal.remote.api

import com.mbglobal.remote.dto.user.UserDto
import com.mbglobal.remote.dto.user.FollowRequestDto
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface FollowerService {

    @GET("followings/")
    fun getUserFollowings(): Single<List<UserDto>>

    @GET("followers/")
    fun getUserFollowers(): Single<List<UserDto>>

    @GET("followings/{id}")
    fun getFollowing(@Path("id") userId: String): Single<UserDto>

    @GET("followers/{id}/")
    fun getFollower(@Path("id") userId: String): Single<UserDto>

    @DELETE("followers/{id}/")
    fun removeFollower(@Path("id") userId: String): Single<ResponseBody>

    @GET("followings/pendings/")
    fun getPendingFollowings(): Single<List<UserDto>>

    @GET("followers/requests/")
    fun getFollowRequests(): Single<List<FollowRequestDto>>

    @PUT("followers/requests/{id}/")
    fun acceptRequest(@Path("id") userId: String): Single<ResponseBody>

    @DELETE("followers/requests/{id}/")
    fun rejectRequest(@Path("id") userId: String): Single<ResponseBody>

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8080/"
    }
}