package com.mbglobal.data.repository

import com.mbglobal.data.datasource.*
import com.mbglobal.data.entity.checkin.CheckinEntity
import com.mbglobal.data.entity.session.SessionEntity
import com.mbglobal.data.entity.user.*
import com.mbglobal.data.mapper.toSessionEntity
import com.mbglobal.data.mapper.toUserLoginItemEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val sessionRemoteDataSource: SessionRemoteDataSource
) {
    fun login(userLoginItemEntity: UserLoginItemEntity): Completable {
        return userRemoteDataSource.login(userLoginItemEntity)
            .flatMapCompletable { userLoginResponseEntity ->
                var s = sessionLocalDataSource.getSession().blockingGet().access
                println(s)
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
        return sessionLocalDataSource.getSession().flatMap {
            sessionRemoteDataSource.refreshSession(it)
        }.flatMap {
            var s = sessionLocalDataSource.getSession().blockingGet().access
            //println(it)
            println(s)
            sessionLocalDataSource.removeSession()
//            sessionLocalDataSource.saveSession(it)
            s = sessionLocalDataSource.getSession().blockingGet().access
            println(s)
            return@flatMap Single.just(it)
        }.map { session: SessionEntity -> session.userId }
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

    fun getEventCheckins(eventId: String): Single<List<CheckinEntity>>{
        return userRemoteDataSource.getEventCheckins(eventId)
    }
}
