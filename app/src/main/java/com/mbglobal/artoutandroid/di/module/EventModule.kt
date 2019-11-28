package com.mbglobal.artoutandroid.di.module

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.remote.datasource.EventRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class EventModule {

    @Binds
    abstract fun bindEventRemoteDataSource(eventRemoteDataSourceImpl: EventRemoteDataSourceImpl) :
            EventRemoteDataSource
}