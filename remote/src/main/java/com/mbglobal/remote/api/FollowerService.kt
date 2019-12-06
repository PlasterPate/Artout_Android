package com.mbglobal.remote.api

import com.mbglobal.data.entity.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import javax.print.DocFlavor

interface FollowerService {

    @GET("followers/")
    fun getUserFollowers(): Single<List<UserEntity>>

    @GET("users/{id}/followers/")
    fun getUserFollowers(userId: String): Single<List<UserEntity>>

    @DELETE("followers/{id}/")
    fun removeFollower(userId: String): Completable

    @GET("followers/requests/")
    fun getFollowRequests(): Single<List<UserEntity>>

    @PUT("followers/requests/{id}/")
    fun acceptRequest(userId: String): Completable

    @DELETE("followers/requests/{id}/")
    fun rejectRequest(userId: String): Completable

//    This method is gonna get completed in Sprint 5
//
//    @GET("followers/{id}/")
//    fun getUserProfile(userId: String)

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8000/"
    }
}