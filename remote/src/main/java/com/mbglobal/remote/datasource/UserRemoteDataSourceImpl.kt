package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.UserRemoteDataSource
import com.mbglobal.data.entity.checkin.CheckinEntity
import com.mbglobal.data.entity.user.*
import com.mbglobal.remote.api.EventService
import com.mbglobal.remote.api.FollowerService
import com.mbglobal.remote.api.UserService
import com.mbglobal.remote.mappers.*
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val userService: UserService,
                                                   private val followerService: FollowerService,
                                                   private val eventService: EventService) :
    UserRemoteDataSource {
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

    override fun getUser(username: String): Single<UserEntity> {
        return followerService.getUser(username).map {
            it.toUserEntity()
        }
    }

    override fun getUserProfile(userId: String?): Single<UserProfileEntity> {
        return eventService.getUserProfile(userId).map {userProfileDto ->
            userProfileDto.toUserProfileEntity()
        }
    }

    override fun searchUser(query: UserSearchEntity): Single<List<UserEntity>>{
        return eventService.searchUser(query.search).map {
            it.map {
                it.toUserEntity()
            }
        }
    }

    override fun getEventCheckins(eventId: String): Single<List<CheckinEntity>> {
        return userService.getEventCheckins(eventId).map {checkins ->
            checkins.map { checkinDto ->
                checkinDto.toCheckinEntity()
            }
        }
    }
}
