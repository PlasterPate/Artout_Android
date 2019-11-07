package com.mbglobal.artoutandroid.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import com.mbglobal.artoutandroid.app.ArtoutApp
import com.mbglobal.artoutandroid.app.MainActivity
import com.mbglobal.artoutandroid.di.module.*
import com.mbglobal.artoutandroid.di.scope.AppScope

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        LoginModule::class,
        RegisterModule::class,
        EventModule::class,
        UserModule::class,
        TokenModule::class,
        NetworkModule::class,
        TimelineModule::class,
        EventsModule::class,
        EventDetailsModule::class,
        ActivityModule::class,
        EventDetailsModule::class,
        ManageEventModule::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<ArtoutApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<ArtoutApp>

}