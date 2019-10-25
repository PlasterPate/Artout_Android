package com.mbglobal.artoutandroid.di.module

import android.content.Context
import android.content.SharedPreferences
import com.mbglobal.artoutandroid.ArtoutApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(app : ArtoutApp) : Context {
        return app.applicationContext
    }

    @Provides
    fun providesSharedPrefrences(context : Context) : SharedPreferences {
        return context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
    }

}