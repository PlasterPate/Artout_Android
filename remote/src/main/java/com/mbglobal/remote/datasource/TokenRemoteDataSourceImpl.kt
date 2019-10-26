package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.TokenRemoteDataSource
import com.mbglobal.remote.api.TokenService
import com.mbglobal.remote.dto.token.RefreshAccessItemDto
import io.reactivex.Single
import javax.inject.Inject

class TokenRemoteDataSourceImpl @Inject constructor(val tokenService: TokenService) :
    TokenRemoteDataSource {

    override fun refreshAccessToken(refresh : String): Single<String?> {
        return tokenService.refreshAccessToken(RefreshAccessItemDto(refresh)).map {
            it.access
        }
    }

}