package com.mbglobal.artoutandroid.di.module

import com.mbglobal.data.datasource.UserLocalDataSource
import com.mbglobal.data.datasource.UserRemoteDataSource
import com.mbglobal.local.datasource.UserLocalDataSourceImpl
import com.mbglobal.remote.datasource.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {

    @Binds
    abstract fun bindUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl) :
            UserRemoteDataSource

    @Binds
    abstract fun bindUserLocalDataSource(userLocalDataSourceImpl: UserLocalDataSourceImpl) :
            UserLocalDataSource

}