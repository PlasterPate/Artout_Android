package com.mbglobal.data.repository

import com.mbglobal.data.datasource.*
import com.mbglobal.data.entity.session.SessionEntity
import com.mbglobal.data.entity.user.*
import com.mbglobal.data.mapper.toSessionEntity
import com.mbglobal.data.mapper.toUserLoginItemEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource
) {
    fun login(userLoginItemEntity: UserLoginItemEntity): Completable {
        return userRemoteDataSource.login(userLoginItemEntity)
            .flatMapCompletable { userLoginResponseEntity ->
                sessionLocalDataSource.saveSession(userLoginResponseEntity.toSessionEntity())
            }
    }

    fun register(userRegisterItemEntity: UserRegisterItemEntity): Completable {
        return userRemoteDataSource.register(userRegisterItemEntity).flatMap {
            with(userRegisterItemEntity.toUserLoginItemEntity()) {
                userRemoteDataSource.login(this)
            }
        }.flatMapCompletable { userLoginResponseEntity ->
            sessionLocalDataSource.saveSession(userLoginResponseEntity.toSessionEntity())
        }
    }

    fun getUser(username: String): Single<UserEntity>{
        return userRemoteDataSource.getUser(username)
    }

    fun getUser(): Single<String> {
        return sessionLocalDataSource.getSession().map { session: SessionEntity -> session.userId }
    }

    fun getUserProfile(userId: String?): Single<UserProfileEntity>{
        return userRemoteDataSource.getUserProfile(userId)
    }

    fun logout(): Completable {
        return sessionLocalDataSource.removeSession()
    }

    fun searchUser(query: UserSearchEntity): Single<List<UserEntity>>{
        return userRemoteDataSource.searchUser(query)
    }
}
