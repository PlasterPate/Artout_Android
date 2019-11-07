package com.mbglobal.artoutandroid.di.module

import androidx.lifecycle.ViewModel
import com.mbglobal.artoutandroid.di.annotation.ViewModelKey
import com.mbglobal.artoutandroid.ui.manageEvent.AddEventFragment
import com.mbglobal.artoutandroid.ui.manageEvent.EditEventFragment
import com.mbglobal.artoutandroid.ui.manageEvent.ManageEventFragment
import com.mbglobal.artoutandroid.ui.manageEvent.ManageEventViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ManageEventModule {

    @ContributesAndroidInjector
    abstract fun addEventFragment() : AddEventFragment

    @ContributesAndroidInjector
    abstract fun editEventFragment() : EditEventFragment

}