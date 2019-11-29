package com.mbglobal.artoutandroid.di.module

import com.mbglobal.data.datasource.UserRemoteDataSource
import com.mbglobal.remote.datasource.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {

    @Binds
    abstract fun bindUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl) :
            UserRemoteDataSource

}