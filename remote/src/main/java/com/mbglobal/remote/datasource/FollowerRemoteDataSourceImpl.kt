package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.FollowerRemoteDataSource
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.remote.api.FollowerService
import com.mbglobal.remote.mappers.toFollowRequestEntity
import com.mbglobal.remote.mappers.toUserEntity
import com.mbglobal.remote.mappers.toFollowRequsetEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FollowerRemoteDataSourceImpl @Inject constructor(
    private val followerService: FollowerService
) : FollowerRemoteDataSource {

    override fun getUserFollowers(userId: String?): Single<List<UserEntity>> {
        return (if (userId.isNullOrBlank())
            followerService.getUserFollowers()
        else
            followerService.getUserFollowers(userId)).map { users ->
            users.map { UserDto ->
                UserDto.toUserEntity()
            }
        }
    }

    override fun getFollower(userId: String): Single<UserEntity> {
        return followerService.getFollower(userId).map { user ->
            user.toUserEntity()
        }
    }

    override fun removeFollower(userId: String): Completable {
        return Completable.fromSingle(followerService.removeFollower(userId))
    }

    override fun getFollowRequests(): Single<List<FollowRequestEntity>> {
        return followerService.getFollowRequests().map { requests ->
            requests.map { requestDto ->
                requestDto.toFollowRequestEntity()
            }
        }
    }

    override fun acceptRequest(userId: String): Completable {
        return Completable.fromSingle(followerService.acceptRequest(userId))
    }

    override fun rejectRequest(userId: String): Completable {
        return Completable.fromSingle(followerService.rejectRequest(userId))
    }
}