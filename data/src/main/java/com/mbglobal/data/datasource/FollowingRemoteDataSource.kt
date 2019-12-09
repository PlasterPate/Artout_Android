package com.mbglobal.data.datasource

import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FollowingRemoteDataSource {

    fun getUserFollowings(): Single<List<UserEntity>>

    fun getUserFollowings(userId: String): Single<List<UserEntity>>

    fun unfollow(userId: String): Completable

    fun follow(userId: String): Completable

    fun getFollowPendings(): Single<List<FollowRequestEntity>>

    fun cancelFollowPending(userId: String): Completable


}