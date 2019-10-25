package com.mbglobal.data.datasource

import io.reactivex.Single

interface TokenLocalDataSource {

    fun saveRefreshToken(refresh : String) : Single<String>

    fun saveAccessToken(access : String) : Single<String>

    fun getAccessToken() : Single<String?>

    fun getRefreshToken() : Single<String?>

}