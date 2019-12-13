package com.mbglobal.artoutandroid.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.user.UserRegisterItemEntity
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    private val _registerStatus : MutableLiveData<Boolean> = MutableLiveData()
    val registerStatus : LiveData<Boolean> = _registerStatus

    private val _registerError : MutableLiveData<String> = MutableLiveData()
    val registerValidateError : LiveData<String> = _registerError

    private val _showLoading: MutableLiveData<Boolean> = MutableLiveData()
    val showLoading: LiveData<Boolean> = _showLoading

    fun onRegisterClicked(userRegisterItemEntity: UserRegisterItemEntity) {
        _showLoading.value = true
        if (validateRegisterInfo(userRegisterItemEntity)) {
            userRepository.register(userRegisterItemEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _showLoading.value = false
                    _registerStatus.value = true
                }, { throwable ->
                    Timber.d("registerthroable"+throwable.message)
                    _showLoading.value = false
                    _registerStatus.value = false
                }).also {
                    compositeDisposable.add(it)
                }
        } else {
            _showLoading.value = false
            _registerError.value = "Invalid register info!"
        }
    }

    fun validateRegisterInfo(userRegisterItemEntity: UserRegisterItemEntity) : Boolean {
        return with(userRegisterItemEntity) {
            username.length > 4 && password.length > 4 && password == passwordConfirm
        }
    }
}
