package com.mbglobal.artoutandroid.di.module

import androidx.lifecycle.ViewModel
import com.mbglobal.artoutandroid.di.annotation.ViewModelKey
import com.mbglobal.artoutandroid.ui.eventDetails.EventDetailsFragment
import com.mbglobal.artoutandroid.ui.eventDetails.EventDetailsViewModel
import com.mbglobal.artoutandroid.ui.manageEvent.EditEventViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class EventDetailsModule {

    @ContributesAndroidInjector
    abstract fun eventDetailsFragment() : EventDetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailsViewModel::class)
    abstract fun bindEventDetailsViewModel(eventViewModel: EditEventViewModel) : ViewModel
}