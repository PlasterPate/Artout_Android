package com.mbglobal.data.repository

import com.mbglobal.data.datasource.FollowerRemoteDataSource
import com.mbglobal.data.datasource.FollowingRemoteDataSource
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.entity.user.FollowRequestEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SocialRepository @Inject constructor(
    private val followerRemoteDataSource: FollowerRemoteDataSource,
    private val followingRemoteDataSource: FollowingRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource
) {

    // FOLLOWERS

    fun getUserFollowers(userId: String?): Single<List<UserEntity>> {
        return if (userId == null)
            followerRemoteDataSource.getUserFollowers()
        else
            followerRemoteDataSource.getUserFollowers(userId)
    }

    fun getFollower(userId: String): Single<UserEntity>{
        return followerRemoteDataSource.getFollower(userId)
    }

    fun removeFollower(userId: String): Completable {
        return followerRemoteDataSource.removeFollower(userId)
    }

    fun getFollowRequests(): Single<List<FollowRequestEntity>> {
        return followerRemoteDataSource.getFollowRequests().map {
            println(it)
            it
        }
    }

    fun acceptRequest(userId: String): Completable {
        return followerRemoteDataSource.acceptRequest(userId)
    }

    fun rejectRequest(userId: String): Completable {
        return followerRemoteDataSource.rejectRequest(userId)
    }

    // FOLLOWINGS

    fun getUserFollowings(userId: String?): Single<List<UserEntity>> {
        //return Single.just(MockUserFactory.getFollowings())
        return followingRemoteDataSource.getUserFollowings(userId)
    }

    fun getFollowing(userId: String): Single<UserEntity>{
        return followingRemoteDataSource.getFollowing(userId)
    }

    fun follow(userId: String): Completable {
        return followingRemoteDataSource.follow(userId)
    }

    fun unfollow(userId: String): Completable {
        return followingRemoteDataSource.unfollow(userId)
    }

    fun getFollowPendings(): Single<List<FollowRequestEntity>> {
        return followingRemoteDataSource.getFollowPendings()
    }

    fun cancelFollowPending(userId: String): Completable {
        return followingRemoteDataSource.cancelFollowPending(userId)
    }
}