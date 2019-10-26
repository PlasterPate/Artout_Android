package com.mbglobal.artoutandroid.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import com.mbglobal.artoutandroid.app.ArtoutApp
import com.mbglobal.artoutandroid.di.module.AppModule
import com.mbglobal.artoutandroid.di.module.LoginModule
import com.mbglobal.artoutandroid.di.module.ViewModelFactoryModule
import com.mbglobal.artoutandroid.di.scope.AppScope

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        LoginModule::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<ArtoutApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<ArtoutApp>

}