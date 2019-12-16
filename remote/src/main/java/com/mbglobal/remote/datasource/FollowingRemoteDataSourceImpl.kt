package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.FollowingRemoteDataSource
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.remote.api.FollowingService
import com.mbglobal.remote.dto.user.UserGetDto
import com.mbglobal.remote.mappers.toFollowRequestEntity
import com.mbglobal.remote.mappers.toUserEntity
import com.mbglobal.remote.mappers.toFollowRequsetEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FollowingRemoteDataSourceImpl @Inject constructor(
    private val followingService: FollowingService
) : FollowingRemoteDataSource {

    override fun getUserFollowings(userId: String?): Single<List<UserEntity>> {
        return (if (userId.isNullOrBlank())
            followingService.getUserFollowings()
        else
            followingService.getUserFollowings(userId)).map { users ->
            users.map { UserDto ->
                UserDto.toUserEntity()
            }
        }
    }


    override fun getFollowing(userId: String): Single<UserEntity> {
        return followingService.getFollowing(userId).map { user ->
            user.toUserEntity()
        }
    }

    override fun follow(userId: String): Completable {
        return Completable.fromSingle(followingService.follow(UserGetDto(userId)))
    }

    override fun unfollow(userId: String): Completable {
        return Completable.fromSingle(followingService.unfollow(userId))
    }

    override fun getFollowPendings(): Single<List<FollowRequestEntity>> {
        return followingService.getFollowPendings().map {requests ->
            requests.map {requestDto ->
                requestDto.toFollowRequestEntity()
            }
        }
    }

    override fun cancelFollowPending(userId: String): Completable {
        return Completable.fromSingle(followingService.cancelFollowPending(userId))
    }
}