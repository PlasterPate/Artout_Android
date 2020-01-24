package com.mbglobal.data.datasource

import com.mbglobal.data.entity.session.SessionEntity
import io.reactivex.Single

interface SessionRemoteDataSource {

    fun refreshSession(sessionEntity: SessionEntity): Single<SessionEntity>
}