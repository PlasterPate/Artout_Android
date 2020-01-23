package com.mbglobal.artoutandroid.di.module

import com.mbglobal.data.datasource.*
import com.mbglobal.local.datasource.SessionLocalDataSourceImpl
import com.mbglobal.local.database.LocalDatabaseDao
import com.mbglobal.local.datasource.EventLocalDataSourceImpl
import com.mbglobal.remote.datasource.*
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

    @Binds
    abstract fun bindFollowersRemoteDataSource(followerRemoteDataSourceImpl: FollowerRemoteDataSourceImpl): FollowerRemoteDataSource

    @Binds
    abstract fun bindFollowingRemoteDataSource(followingRemoteDataSourceImpl: FollowingRemoteDataSourceImpl): FollowingRemoteDataSource

    @Binds
    abstract fun bindTimelineRemoteDataSource(timelineRemoteDataSource: TimelineRemoteDataSourceImpl): TimelineRemoteDataSource
}