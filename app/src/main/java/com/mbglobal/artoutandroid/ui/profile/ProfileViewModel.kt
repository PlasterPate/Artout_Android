package com.mbglobal.artoutandroid.ui.profile

import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.app.LiveEvent
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.user.FollowRequestEntity
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.repository.EventRepository
import com.mbglobal.data.repository.SocialRepository
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val socialRepository: SocialRepository
) : BaseViewModel() {

    private val _logoutStatus: MutableLiveData<LiveEvent<Boolean>> = MutableLiveData()
    val logoutStatus = _logoutStatus

    private val _logoutError: MutableLiveData<String> = MutableLiveData()
    val logoutError = _logoutError

    private val _followStatus: MutableLiveData<LiveEvent<Boolean>> = MutableLiveData()
    val followStatus = _followStatus

    fun clickLogout() {
        userRepository.logout().subscribe({
            _logoutStatus.value = LiveEvent(true)
        }, {
            _logoutStatus.value = LiveEvent(false)
            _logoutError.value = "Logout failed"
        }).also {
            compositeDisposable.add(it)
        }
    }

    fun sendFollowRequest(userId: String) {
//        userRepository.getUser(userId)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
                socialRepository.follow(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _followStatus.value = LiveEvent(true)
                    }, {
                        _followStatus.value = LiveEvent(false)
                    })
//            },{})
            .also {
                compositeDisposable.add(it)
            }

    }
}