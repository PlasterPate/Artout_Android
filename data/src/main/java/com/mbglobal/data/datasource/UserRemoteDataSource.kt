package com.mbglobal.data.datasource

import com.mbglobal.data.entity.user.*
import io.reactivex.Completable
import io.reactivex.Single

interface UserRemoteDataSource {

    fun login(userLoginItemEntity: UserLoginItemEntity) : Single<UserLoginResponseEntity>

    fun register(userLoginRegisterItemEntity: UserRegisterItemEntity) : Single<UserRegisterResponseEntity>

    fun getUser(id : String, token : String) : Single<UserEntity>

    fun follow(username: String): Completable

    fun getFollowers(userId: Int): Single<List<UserEntity>>

    fun getFollowings(userId: Int): Single<List<UserEntity>>

    fun getFollowRequests(userId: Int): Single<List<UserEntity>>?

}