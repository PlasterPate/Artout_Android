package com.mbglobal.data.repository

import com.mbglobal.data.datasource.FollowerRemoteDataSource
import com.mbglobal.data.datasource.FollowingRemoteDataSource
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.entity.user.FollowItemEntity
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SocialRepository @Inject constructor(
    private val followerRemoteDataSource: FollowerRemoteDataSource,
    private val followingRemoteDataSource: FollowingRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource
) {

    // FOLLOWERS

    fun getUserFollowers(): Single<List<FollowItemEntity>> {
        return followerRemoteDataSource.getUserFollowers()
    }

    fun getUserFollowers(userId: String?): Single<List<FollowItemEntity>> {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMap {
            followerRemoteDataSource.getUserFollowers(it)
        }
    }

    fun getFollower(userId: String?): Single<UserEntity>{
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMap {
            followerRemoteDataSource.getFollower(it)
        }
    }

    fun removeFollower(userId: String?): Completable {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMapCompletable {
            followerRemoteDataSource.removeFollower(it)
        }
    }

    fun getFollowRequests(): Single<List<FollowRequestEntity>> {
        return followerRemoteDataSource.getFollowRequests()
    }

    fun acceptRequest(userId: String?): Completable {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMapCompletable {
            followerRemoteDataSource.acceptRequest(it)
        }
    }

    fun rejectRequest(userId: String?): Completable {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMapCompletable {
            followerRemoteDataSource.rejectRequest(it)
        }
    }

    // FOLLOWINGS

    fun getUserFollowings(): Single<List<FollowItemEntity>> {
        return followingRemoteDataSource.getUserFollowings()
    }

    fun getUserFollowings(userId: String?): Single<List<FollowItemEntity>> {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMap {
            followingRemoteDataSource.getUserFollowings(it)
        }
    }

    fun getFollowing(userId: String?): Single<UserEntity>{
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMap {
            followingRemoteDataSource.getFollowing(it)
        }
    }

    fun follow(userId: String?): Completable {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMapCompletable {
            followingRemoteDataSource.follow(it)
        }
    }

    fun unfollow(userId: String?): Completable {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMapCompletable {
            followingRemoteDataSource.unfollow(it)
        }
    }

    fun getFollowPendings(): Single<List<FollowRequestEntity>> {
        return followingRemoteDataSource.getFollowPendings()
    }

    fun cancelFollowPending(userId: String?): Completable {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMapCompletable {
            followingRemoteDataSource.cancelFollowPending(it)
        }
    }
}