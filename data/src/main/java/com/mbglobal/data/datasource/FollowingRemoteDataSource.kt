package com.mbglobal.data.datasource

import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.entity.user.FollowRequestEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FollowingRemoteDataSource {

    fun getUserFollowings(userId: String? = null): Single<List<UserEntity>>

    fun getFollowing(userId: String): Single<UserEntity>

    fun unfollow(userId: String): Completable

    fun follow(userId: String): Completable

    fun getFollowPendings(): Single<List<FollowRequestEntity>>

    fun cancelFollowPending(userId: String): Completable


}