package com.mbglobal.remote.api

import com.mbglobal.remote.dto.token.RefreshAccessItemDto
import com.mbglobal.remote.dto.token.RefreshAccessResponseDto
import com.mbglobal.remote.dto.token.TokenItemDto
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TokenService {

    @POST("/api/token/refresh/")
    fun refreshAccessToken(@Body refreshAccessItemDto : RefreshAccessItemDto) : Single<RefreshAccessResponseDto?>

    @POST("/api/notification/token/")
    fun sendAccessToken(@Body tokenItemDto : TokenItemDto, @Header("Authorization") bearerToken: String) : Single<Unit>

    companion object {
        const val BASE_URL = "http://artoutapi.pagekite.me/"
    }

}