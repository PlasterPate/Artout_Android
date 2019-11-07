package com.mbglobal.artoutandroid.di.module

import com.mbglobal.artoutandroid.ui.addevent.AddEventFragment
import com.mbglobal.artoutandroid.ui.editevent.EditEventFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ManageEventModule {

    @ContributesAndroidInjector
    abstract fun addEventFragment() : AddEventFragment

    @ContributesAndroidInjector
    abstract fun editEventFragment() : EditEventFragment

}