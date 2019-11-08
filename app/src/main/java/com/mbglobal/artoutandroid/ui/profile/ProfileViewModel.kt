package com.mbglobal.artoutandroid.ui.profile

import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val eventRepository: EventRepository,
                                           private val userRepository: UserRepository) : BaseViewModel() {

    var eventToAdd = MutableLiveData<EventEntity>()

    fun getUserEvents(userId : String?) {
        eventRepository.getUserEvents(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({eventEntity : EventEntity ->
                eventToAdd.value = eventEntity
            }, { it ->
                println(it)
            }).also {
                compositeDisposable.add(it)
            }
    }

    private val _logoutStatus : MutableLiveData<Boolean> = MutableLiveData()
    val logoutStatus = _logoutStatus

    private val _logoutError : MutableLiveData<String> = MutableLiveData()
    val logoutError = _logoutError

    fun clickLogout() {
        userRepository.logout().subscribe({
            _logoutStatus.value = true
        }, {
            _logoutStatus.value = false
            _logoutError.value = "Logout failed"
        }).also {
            compositeDisposable.add(it)
        }
    }
}