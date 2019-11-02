package com.mbglobal.artoutandroid.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.user.UserRegisterItemEntity
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    private val _registerStatus : MutableLiveData<Boolean> = MutableLiveData()
    val registerStatus : LiveData<Boolean> = _registerStatus

    private val _registerError : MutableLiveData<String> = MutableLiveData()
    val registerValidateError : LiveData<String> = _registerError

    fun onRegisterClicked(userRegisterItemEntity: UserRegisterItemEntity) {
        if (validateRegisterInfo(userRegisterItemEntity)) {
            userRepository.register(userRegisterItemEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userRegisterResponseEntity ->
                    _registerStatus.value = true
                }, { throwable ->
                    _registerStatus.value = false
                }).also {
                    compositeDisposable.add(it)
                }
        } else {
            _registerError.value = "Invalid register info!"
        }
    }

    fun validateRegisterInfo(userRegisterItemEntity: UserRegisterItemEntity) : Boolean {
        return with(userRegisterItemEntity) {
            username.isNotEmpty() && password.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty()
                    && username.all { it.isDigit() } && password.length > 4
        }
    }
}
