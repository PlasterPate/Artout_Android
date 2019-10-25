package com.mbglobal.data.datasource

import io.reactivex.Single

interface TokenRemoteDataSource {

    fun refreshAccessToken(refresh : String) : Single<String?>

}