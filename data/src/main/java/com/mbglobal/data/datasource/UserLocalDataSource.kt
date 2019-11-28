package com.mbglobal.data.datasource

import io.reactivex.Completable
import io.reactivex.Single

interface UserLocalDataSource {

    fun getUser(): Single<String?>

    fun saveUser(userName: String): Single<Unit>

    fun removeUser(): Completable
    
}