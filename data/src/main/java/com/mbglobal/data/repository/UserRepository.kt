package com.mbglobal.data.repository

import com.mbglobal.data.datasource.*
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.user.*
import com.mbglobal.data.mapper.toUserRegisterResponseEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val tokenRemoteDataSource: TokenRemoteDataSource,
    private val tokenLocalDataSource: TokenLocalDataSource,
    private val eventRemoteDataSource: EventRemoteDataSource
) {
    fun login(userLoginItemEntity: UserLoginItemEntity): Single<Unit> {
        return userRemoteDataSource.login(userLoginItemEntity).flatMap { userLoginResponseEntity ->
            tokenLocalDataSource.saveAccessToken(access = userLoginResponseEntity.access)
                .flatMap { tokenLocalDataSource.saveRefreshToken(refresh = userLoginResponseEntity.refresh) }
                .flatMap { userLocalDataSource.saveUser(userLoginResponseEntity.id) }
        }
    }

    fun register(userRegisterItemEntity: UserRegisterItemEntity): Single<UserRegisterResponseEntity> {
        return userRemoteDataSource.register(userRegisterItemEntity).flatMap { registerResponse ->
            val userLoginItemEntity = UserLoginItemEntity(
                userRegisterItemEntity.username,
                userRegisterItemEntity.password
            )
            userRemoteDataSource.login(userLoginItemEntity)
        }.flatMap { loginResponse ->
            tokenLocalDataSource.saveRefreshToken(loginResponse.access).flatMap {
                tokenLocalDataSource.saveRefreshToken(loginResponse.refresh)
            }.flatMap {
                userLocalDataSource.saveUser(loginResponse.id)
            }.flatMap { it ->
                Single.just(loginResponse)
            }
        }.map { userLoginResponse ->
            userLoginResponse.toUserRegisterResponseEntity()
        }
    }

    fun getUser(): Single<String?> {
        return userLocalDataSource.getUser()
    }

    fun getUser(id: String): Single<UserEntity> {
        return tokenLocalDataSource.getAccessToken().flatMap { token: String ->
            userRemoteDataSource.getUser(id, token).doOnError {
                println("first error -> ${it.message}")
            }.onErrorResumeNext { it ->
                tokenLocalDataSource.getRefreshToken().doOnSuccess {
                    println("got refresh $it from local")
                }
                    .flatMap { refresh ->
                        tokenLocalDataSource.saveRefreshToken(refresh)
                    }
                    .flatMap { refresh ->
                        tokenRemoteDataSource.refreshAccessToken(refresh).doOnSuccess {
                            println("got access $it with the refresh key")
                        }.flatMap { access ->
                            tokenLocalDataSource.saveAccessToken(access)
                        }.flatMap { access ->
                            println("re: access was $access")
                            userRemoteDataSource.getUser(id, access)
                        }
                    }
            }
        }
    }

    fun getUserEvents(): Single<List<EventEntity>> {
        return userLocalDataSource.getUser().flatMap {
            eventRemoteDataSource.getUserEvents(it.toInt())
        }
    }

    fun logout(): Completable {
        return tokenLocalDataSource.removeAllCredentials().andThen(
            userLocalDataSource.removeUser()
        )
    }
}
