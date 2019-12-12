package com.mbglobal.remote.api

import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.remote.dto.user.FollowItemDto
import com.mbglobal.remote.dto.user.FollowRequestDto
import com.mbglobal.remote.dto.user.UserDto
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*
import javax.print.DocFlavor

interface FollowerService {

    @GET("followers/")
    fun getUserFollowers(): Single<List<FollowItemDto>>

    @GET("users/{id}/followers/")
    fun getUserFollowers(@Path("id") userId: String): Single<List<FollowItemDto>>

    @GET("followers/{id}/")
    fun getFollower(@Path("id") userId: String): Single<UserDto>

    @DELETE("followers/{id}/")
    fun removeFollower(@Path("id") userId: String): Single<ResponseBody>

    @GET("followers/requests/")
    fun getFollowRequests(): Single<List<FollowRequestDto>>

    @PUT("followers/requests/{id}/")
    fun acceptRequest(@Path("id") userId: String): Single<ResponseBody>

    @DELETE("followers/requests/{id}/")
    fun rejectRequest(@Path("id") userId: String): Single<ResponseBody>

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8000/"
    }
}