package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.SessionRemoteDataSource
import com.mbglobal.data.entity.session.SessionEntity
import com.mbglobal.remote.api.TokenService
import com.mbglobal.remote.dto.token.RefreshAccessItemDto
import io.reactivex.Single
import javax.inject.Inject

class SessionRemoteDataSourceImpl @Inject constructor(private val tokenService: TokenService) : SessionRemoteDataSource {

    override fun refreshSession(sessionEntity: SessionEntity): Single<SessionEntity> {
        return tokenService.refreshAccessToken(RefreshAccessItemDto(sessionEntity.refresh)).map {
            sessionEntity.copy(access = it.access)
        }
    }
}