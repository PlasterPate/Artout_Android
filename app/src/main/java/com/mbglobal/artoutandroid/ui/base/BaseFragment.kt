package com.mbglobal.artoutandroid.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(), LifecycleOwner {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
}