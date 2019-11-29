package com.mbglobal.data.datasource

import com.mbglobal.data.entity.session.SessionEntity
import io.reactivex.Completable
import io.reactivex.Single

interface SessionLocalDataSource {

    fun saveSession(sessionEntity: SessionEntity): Completable

    fun getSession(): Single<SessionEntity>

    fun removeSession(): Completable
}