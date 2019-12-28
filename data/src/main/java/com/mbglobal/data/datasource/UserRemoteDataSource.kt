package com.mbglobal.data.datasource

import com.mbglobal.data.entity.user.*
import io.reactivex.Single

interface UserRemoteDataSource {

    fun login(userLoginItemEntity: UserLoginItemEntity): Single<UserLoginResponseEntity>

    fun register(userLoginRegisterItemEntity: UserRegisterItemEntity): Single<UserRegisterResponseEntity>

    fun getUser(username: String): Single<UserEntity>

    fun getUserProfile(userId: String?): Single<UserProfileEntity>

    fun searchUser(query: UserSearchEntity): Single<List<UserEntity>>
}