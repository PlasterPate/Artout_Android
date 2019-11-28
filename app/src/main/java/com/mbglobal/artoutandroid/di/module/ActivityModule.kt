package com.mbglobal.artoutandroid.di.module

import com.mbglobal.artoutandroid.app.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity() : MainActivity

}