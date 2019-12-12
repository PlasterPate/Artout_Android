package com.mbglobal.data.datasource

import com.mbglobal.data.entity.user.FollowItemEntity
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FollowerRemoteDataSource {

    fun getUserFollowers(): Single<List<FollowItemEntity>>

    fun getUserFollowers(userId: String): Single<List<FollowItemEntity>>

    fun getFollower(userId: String): Single<UserEntity>

    fun removeFollower(userId: String): Completable

    fun getFollowRequests(): Single<List<FollowRequestEntity>>

    fun acceptRequest(userId: String): Completable

    fun rejectRequest(userId: String): Completable

}