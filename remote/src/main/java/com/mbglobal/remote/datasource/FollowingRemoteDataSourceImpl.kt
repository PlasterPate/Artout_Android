package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.FollowingRemoteDataSource
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.remote.api.FollowingService
import com.mbglobal.remote.mappers.toFollowRequsetEntity
import com.mbglobal.remote.mappers.toUserEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FollowingRemoteDataSourceImpl @Inject constructor(
    private val followingService: FollowingService
) : FollowingRemoteDataSource {

    override fun getUserFollowings(): Single<List<UserEntity>> {
        return followingService.getUserFollowings().map { users ->
            users.map { userDto ->
                userDto.toUserEntity()
            }
        }
    }

    override fun getUserFollowings(userId: String): Single<List<UserEntity>> {
        return followingService.getUserFollowings(userId).map { users ->
            users.map { userDto ->
                userDto.toUserEntity()
            }
        }
    }

    override fun getFollowing(userId: String): Single<UserEntity> {
        return followingService.getFollowing(userId).map {user ->
            user.toUserEntity()
        }
    }

    override fun follow(userId: String): Completable {
        return Completable.fromSingle(followingService.follow(userId))
    }

    override fun unfollow(userId: String): Completable {
        return Completable.fromSingle(followingService.unfollow(userId))
    }

    override fun getFollowPendings(): Single<List<FollowRequestEntity>> {
        return followingService.getFollowPendings().map { requests ->
            requests.map { requestDto ->
                requestDto.toFollowRequsetEntity()
            }
        }
    }

    override fun cancelFollowPending(userId: String): Completable {
        return Completable.fromSingle(followingService.cancelFollowPending(userId))
    }
}