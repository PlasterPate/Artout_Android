package com.mbglobal.artoutandroid.di.module

import androidx.lifecycle.ViewModel
import com.mbglobal.artoutandroid.di.annotation.ViewModelKey
import com.mbglobal.artoutandroid.ui.eventlist.EventListFragment
import com.mbglobal.artoutandroid.ui.eventlist.EventListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun eventListFragment(): EventListFragment

}