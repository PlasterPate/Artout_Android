package com.mbglobal.artoutandroid.app

import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    private val _loginStatus: MutableLiveData<Boolean> = MutableLiveData()
    val loginStatus = _loginStatus

    fun onCreate() {
        userRepository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { userdId ->
                _loginStatus.value = !(userdId.isNullOrBlank())
            }.also {
                compositeDisposable.add(it)
            }
    }

}