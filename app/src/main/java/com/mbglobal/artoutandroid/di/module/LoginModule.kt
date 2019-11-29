package com.mbglobal.artoutandroid.di.module

import androidx.lifecycle.ViewModel
import com.mbglobal.artoutandroid.di.annotation.ViewModelKey
import com.mbglobal.artoutandroid.ui.login.LoginFragment
import com.mbglobal.artoutandroid.ui.login.LoginViewModel
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.datasource.UserRemoteDataSource
import com.mbglobal.local.datasource.SessionLocalDataSourceImpl
import com.mbglobal.remote.datasource.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindSessionLocalDataSource(sessionLocalDataSourceImpl: SessionLocalDataSourceImpl) : SessionLocalDataSource

}