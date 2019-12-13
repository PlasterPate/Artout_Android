package com.mbglobal.artoutandroid.di.module

import android.content.Context
import androidx.room.Room
import com.mbglobal.artoutandroid.di.scope.AppScope
import com.mbglobal.local.database.LocalDatabase
import dagger.Module
import dagger.Provides
import com.mbglobal.local.database.LocalDatabaseDao
import javax.inject.Singleton


@Module
class DatabaseModule {

    @AppScope
    @Provides
    fun providesLocalDatabase(context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context, LocalDatabase::class.java, "local_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesLocalDatabaseDao(database: LocalDatabase): LocalDatabaseDao{
        return database.localDatabaseDao
    }
}