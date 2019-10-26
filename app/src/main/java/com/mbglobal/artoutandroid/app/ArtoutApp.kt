package com.mbglobal.artoutandroid.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import com.mbglobal.artoutandroid.di.component.AppComponent
import com.mbglobal.artoutandroid.di.component.DaggerAppComponent

class ArtoutApp : DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instanse = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val injector = DaggerAppComponent.factory().create(this)
        appComponent = injector as AppComponent
        return injector
    }

    companion object {
        lateinit var instanse: ArtoutApp
    }
}