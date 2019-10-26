package com.mbglobal.artoutandroid.di.module

import com.mbglobal.data.datasource.TokenLocalDataSource
import com.mbglobal.data.datasource.TokenRemoteDataSource
import com.mbglobal.local.datasource.TokenLocalDataSourceImpl
import com.mbglobal.remote.datasource.TokenRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class TokenModule {

    @Binds
    abstract fun bindTokenRemoteDataSource(tokenRemoteDataSourceImpl: TokenRemoteDataSourceImpl) :
            TokenRemoteDataSource

    @Binds
    abstract fun bindTokenLocalDataSource(tokenLocalDataSourceImpl: TokenLocalDataSourceImpl) :
            TokenLocalDataSource

}