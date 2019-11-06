package com.mbglobal.artoutandroid.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import com.mbglobal.artoutandroid.app.ArtoutApp
import com.mbglobal.artoutandroid.di.scope.AppScope

@Module
class AppModule {

    @AppScope
    @Provides
    fun provideContext(app: ArtoutApp): Context = app.applicationContext

    @Provides
    fun providesSharedPrefrences(context : Context) : SharedPreferences {
        return context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
    }
}