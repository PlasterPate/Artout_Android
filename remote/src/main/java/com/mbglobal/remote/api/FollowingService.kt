package com.mbglobal.remote.api

import com.mbglobal.data.entity.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface FollowingService {

    @GET("followings/")
    fun getUserFollowings(): Single<List<UserEntity>>

    @GET("users/{id}/followings")
    fun getUserFollowings(userId: String): Single<List<UserEntity>>

    @DELETE("followings/{id}/")
    fun unfollow(userId: String): Completable

    @POST("followings/pendings/")
    fun follow(userId: String): Completable

    @GET("followings/pendings/")
    fun getFollowPendings(): Single<List<UserEntity>>

    @DELETE("followings/pendings/{id}/")
    fun cancelFollowPending(userId: String): Completable

//    This method is gonna get completed in Sprint 5
//
//    @GET("followings/{id}/")
//    fun getUserProfile(userId: String)

    companion object {
        const val BASE_URL: String = "http://35.202.66.168:8000/"
    }
}