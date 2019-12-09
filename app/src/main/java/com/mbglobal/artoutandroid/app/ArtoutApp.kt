package com.mbglobal.artoutandroid.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import com.mbglobal.artoutandroid.di.component.AppComponent
import com.mbglobal.artoutandroid.di.component.DaggerAppComponent
import timber.log.Timber.DebugTree
import timber.log.Timber
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.mbglobal.artoutandroid.BuildConfig



class ArtoutApp : DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val injector = DaggerAppComponent.factory().create(this)
        appComponent = injector as AppComponent
        return injector
    }

    companion object {
        lateinit var instance: ArtoutApp
    }
}