package com.mbglobal.artoutandroid.di.module

import com.mbglobal.artoutandroid.ui.addevent.AddEventFragment
import com.mbglobal.artoutandroid.ui.discover.DiscoverFragment
import com.mbglobal.artoutandroid.ui.editevent.EditEventFragment
import com.mbglobal.artoutandroid.ui.eventdetails.EventDetailsFragment
import com.mbglobal.artoutandroid.ui.eventlist.EventListFragment
import com.mbglobal.artoutandroid.ui.login.LoginFragment
import com.mbglobal.artoutandroid.ui.profile.ProfileFragment
import com.mbglobal.artoutandroid.ui.profile.UserProfileFragment
import com.mbglobal.artoutandroid.ui.register.RegisterFragment
import com.mbglobal.artoutandroid.ui.search.SearchResultFragment
import com.mbglobal.artoutandroid.ui.timeline.TimelineFragment
import com.mbglobal.artoutandroid.ui.users.followers.FollowersFragment
import com.mbglobal.artoutandroid.ui.users.followings.FollowingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun eventListFragment(): EventListFragment

    @ContributesAndroidInjector
    abstract fun eventDetailsFragment(): EventDetailsFragment

    @ContributesAndroidInjector
    abstract fun discoverFragment(): DiscoverFragment

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun addEventFragment(): AddEventFragment

    @ContributesAndroidInjector
    abstract fun editEventFragment(): EditEventFragment

    @ContributesAndroidInjector
    abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun userProfileFragment(): UserProfileFragment

    @ContributesAndroidInjector
    abstract fun registerFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun timelineFragment(): TimelineFragment

    @ContributesAndroidInjector
    abstract fun followersFragment(): FollowersFragment

    @ContributesAndroidInjector
    abstract fun followingsFragment(): FollowingsFragment

    @ContributesAndroidInjector
    abstract fun searchResultFragment(): SearchResultFragment

}