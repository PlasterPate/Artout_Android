package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.UserRemoteDataSource
import com.mbglobal.data.entity.user.*
import com.mbglobal.remote.api.UserService
import com.mbglobal.remote.dto.user.UserGetDto
import com.mbglobal.remote.mappers.*
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val userService: UserService) :
    UserRemoteDataSource {
    override fun getUser(id: String, token: String): Single<UserEntity> {
        return userService.getUser(UserGetDto(id), "Bearer $token").map {
            it.toUserEntity()
        }
    }

    override fun login(userLoginItemEntity: UserLoginItemEntity): Single<UserLoginResponseEntity> {
        return userService.login(userLoginItemEntity.toUserLoginItemDto()).map {
            it.toUserLoginResponseEntity()
        }
    }

    override fun register(userLoginRegisterItemEntity: UserRegisterItemEntity): Single<UserRegisterResponseEntity> {
        return userService.register(userLoginRegisterItemEntity.toUserRegisterItemDto()).map {
            it.toUserRegisterResponseEntity()
        }
    }

    override fun follow(username: String): Completable {
        return userService.follow(username)
    }

    override fun getFollowers(userId: Int): Single<List<UserEntity>> {
        return userService.getFollowers(userId)
    }

    override fun getFollowings(userId: Int): Single<List<UserEntity>> {
        return userService.getFollowings(userId)
    }

    override fun getFollowRequests(userId: Int): Single<List<UserEntity>>? {
        return userService.getFollowRequests(userId)
    }

}
