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

    fun getUserFollowers(): Single<List<UserEntity>> {
        return followerRemoteDataSource.getUserFollowers()
    }

    fun getUserFollowers(userId: String?): Single<List<UserEntity>> {
        return Single.just(MockUserFactory.getFollowers())
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
        return Single.just(MockUserFactory.getFollowRequests())
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

    fun getUserFollowings(): Single<List<UserEntity>> {
        return followingRemoteDataSource.getUserFollowings()
    }

    fun getUserFollowings(userId: String?): Single<List<UserEntity>> {
        return Single.just(MockUserFactory.getFollowings())
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