package com.mbglobal.artoutandroid.di.module

import androidx.lifecycle.ViewModel
import com.mbglobal.artoutandroid.app.MainViewModel
import com.mbglobal.artoutandroid.di.annotation.ViewModelKey
import com.mbglobal.artoutandroid.ui.addevent.AddEventViewModel
import com.mbglobal.artoutandroid.ui.discover.DiscoverViewModel
import com.mbglobal.artoutandroid.ui.editevent.EditEventViewModel
import com.mbglobal.artoutandroid.ui.eventcheckinlist.EventCheckinListViewModel
import com.mbglobal.artoutandroid.ui.eventdetails.EventDetailsViewModel
import com.mbglobal.artoutandroid.ui.eventlist.EventListViewModel
import com.mbglobal.artoutandroid.ui.login.LoginViewModel
import com.mbglobal.artoutandroid.ui.profile.ProfileViewModel
import com.mbglobal.artoutandroid.ui.register.RegisterViewModel
import com.mbglobal.artoutandroid.ui.timeline.TimelineViewModel
import com.mbglobal.artoutandroid.ui.users.SocialViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventListViewModel::class)
    abstract fun bindEventListViewModel(eventListViewModel: EventListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailsViewModel::class)
    abstract fun bindEventDetailsViewModel(eventDetailsViewModel: EventDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindDiscoverViewModel(discoverViewModel: DiscoverViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TimelineViewModel::class)
    abstract fun bindTimelineViewModel(viewModel: TimelineViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditEventViewModel::class)
    abstract fun bindEditEventViewModel(editEventViewModel: EditEventViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddEventViewModel::class)
    abstract fun bindAddEventViewModel(addEventViewModel: AddEventViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SocialViewModel::class)
    abstract fun bindSocialViewModel(socialViewModel: SocialViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventCheckinListViewModel::class)
    abstract fun bindEventCheckinListViewModel(eventCheckinListViewModel: EventCheckinListViewModel): ViewModel

}