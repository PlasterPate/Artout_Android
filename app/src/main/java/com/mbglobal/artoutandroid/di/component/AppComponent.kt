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
        LoginModule::class,
        RegisterModule::class,
        UserModule::class,
        TokenModule::class,
        NetworkModule::class,
        TimelineModule::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<ArtoutApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<ArtoutApp>

}