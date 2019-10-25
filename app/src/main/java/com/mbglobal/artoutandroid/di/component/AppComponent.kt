package com.mbglobal.artoutandroid.di.component

import com.mbglobal.artoutandroid.di.module.AppModule
import com.mbglobal.artoutandroid.ArtoutApp
import com.mbglobal.artoutandroid.di.module.ActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityModule::class
])
interface AppComponent : AndroidInjector<ArtoutApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<ArtoutApp>
}