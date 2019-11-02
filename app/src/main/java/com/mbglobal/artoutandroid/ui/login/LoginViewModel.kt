package com.mbglobal.artoutandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.user.UserLoginItemEntity
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    private val _loginError : MutableLiveData<String> = MutableLiveData()
    val loginError : LiveData<String> = _loginError

    private val _loginStatus : MutableLiveData<Boolean> = MutableLiveData()
    val loginStatus : LiveData<Boolean> = _loginStatus

    private val _showLoading : MutableLiveData<Boolean> = MutableLiveData()
    val showLoading : LiveData<Boolean> = _showLoading

    fun onLoginClicked(userName : String, password : String) {

        val userLoginItemEntity = UserLoginItemEntity(userName, password)


        if (isLoginInfoValid(userLoginItemEntity)) {
            _showLoading.value = true

            userRepository.login(userLoginItemEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { userLoginResponseEntity ->
                        _showLoading.value = false
                        _loginStatus.value = true
                    },
                    { throwable ->
                        _showLoading.value = false
                        _loginStatus.value = false
                    }
                ).also {
                    compositeDisposable.add(it)
                }
        }

    }

    companion object {

        private fun isLoginInfoValid(userLoginItemEntity: UserLoginItemEntity): Boolean {
            return (userLoginItemEntity.password.isNotEmpty())
                    && (userLoginItemEntity.username.isNotEmpty())
        }

    }
}
