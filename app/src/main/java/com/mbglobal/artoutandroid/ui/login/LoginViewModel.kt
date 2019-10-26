package com.mbglobal.artoutandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbglobal.data.entity.user.UserLoginItemEntity
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _loginError : MutableLiveData<String> = MutableLiveData()
    val loginError : LiveData<String> = _loginError

    private val _loginStatus : MutableLiveData<Boolean> = MutableLiveData()
    val loginStatus : LiveData<Boolean> = _loginStatus

    fun onLoginClicked(userName : String, password : String) {

        val userLoginItemEntity = UserLoginItemEntity(userName, password)

        if (isLoginInfoValid(userLoginItemEntity)) {
            userRepository.login(userLoginItemEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { userLoginResponseEntity ->
                        _loginStatus.value = true
                    },
                    { throwable ->
                        _loginStatus.value = false
                    }
                ).also {
                    it.dispose()
                }
        }

    }

    fun isLoginInfoValid(userLoginItemEntity: UserLoginItemEntity): Boolean {
        return (userLoginItemEntity.password.isNotEmpty())
                && (userLoginItemEntity.username.isNotEmpty())
    }
}