package com.mbglobal.artoutandroid.di.module

import com.mbglobal.data.datasource.EventLocalDataSource
import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.datasource.UserRemoteDataSource
import com.mbglobal.local.datasource.SessionLocalDataSourceImpl
import com.mbglobal.local.database.LocalDatabaseDao
import com.mbglobal.local.datasource.EventLocalDataSourceImpl
import com.mbglobal.remote.datasource.EventRemoteDataSourceImpl
import com.mbglobal.remote.datasource.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindEventLocalDataSource(eventLocalDataSourceImpl: EventLocalDataSourceImpl):
            EventLocalDataSource

    @Binds
    abstract fun bindEventRemoteDataSource(eventRemoteDataSourceImpl: EventRemoteDataSourceImpl):
            EventRemoteDataSource

    @Binds
    abstract fun bindSessionLocalDataSource(sessionLocalDataSourceImpl: SessionLocalDataSourceImpl): SessionLocalDataSource

    @Binds
    abstract fun bindUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl):
            UserRemoteDataSource
}