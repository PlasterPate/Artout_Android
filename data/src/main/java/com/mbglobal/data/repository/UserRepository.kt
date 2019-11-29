package com.mbglobal.data.repository

import com.mbglobal.data.datasource.*
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.session.SessionEntity
import com.mbglobal.data.entity.user.*
import com.mbglobal.data.mapper.toSessionEntity
import com.mbglobal.data.mapper.toUserLoginItemEntity
import com.mbglobal.data.mapper.toUserRegisterResponseEntity
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource
) {
    fun login(userLoginItemEntity: UserLoginItemEntity): Completable {
        return userRemoteDataSource.login(userLoginItemEntity).flatMapCompletable { userLoginResponseEntity ->
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

    fun getUser(): Single<String?> {
        return userLocalDataSource.getUser()
    }

    fun getUser(): Single<String> {
        return sessionLocalDataSource.getSession().map { session:SessionEntity -> session.userId }
    }

    fun logout() : Completable {
        return sessionLocalDataSource.removeSession()
    }

    fun follow(username: String): Completable {
        return userRemoteDataSource.follow(username)
    }

    fun getFollowers(userId: String?): Single<List<UserEntity>> {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: userLocalDataSource.getUser()
        return idSingle.flatMap {
            userRemoteDataSource.getFollowers(it.toInt())
        }
    }

    fun getFollowings(userId: String?): Single<List<UserEntity>> {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: userLocalDataSource.getUser()
        return idSingle.flatMap {
            userRemoteDataSource.getFollowings(it.toInt())
        }
    }

    fun getFollowRequests(): Single<List<UserEntity>> {
        return userLocalDataSource.getUser().flatMap {
            userRemoteDataSource.getFollowRequests(it.toInt())
        }
    }
}
