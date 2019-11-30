package com.mbglobal.artoutandroid.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import com.mbglobal.artoutandroid.app.ArtoutApp
import com.mbglobal.artoutandroid.di.module.*
import com.mbglobal.artoutandroid.di.scope.AppScope

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        ActivityModule::class,
        FragmentsModule::class,
        DataModule::class,
        ViewModelModule::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<ArtoutApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<ArtoutApp>

}