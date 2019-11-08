package com.mbglobal.artoutandroid.di.module

import androidx.lifecycle.ViewModel
import com.mbglobal.artoutandroid.di.annotation.ViewModelKey
import com.mbglobal.artoutandroid.ui.addevent.AddEventFragment
import com.mbglobal.artoutandroid.ui.editevent.EditEventFragment
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(ManageEventViewModel::class)
    abstract fun bindManageEventViewModel(manageEventViewModel: ManageEventViewModel) : ViewModel

}