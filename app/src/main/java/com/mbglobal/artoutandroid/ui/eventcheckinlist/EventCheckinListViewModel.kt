package com.mbglobal.artoutandroid.ui.eventcheckinlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class EventCheckinListViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private var _users = MutableLiveData<List<UserEntity>>()
    var users: LiveData<List<UserEntity>> = _users

    private var _eventId: String? = null

    fun loadCheckedInUsers() {
//        _users.value = listOf(
//            UserEntity(4, "", "Ali", "Movahed", "amma", UserState.NOT_FOLLOWING),
//            UserEntity(12, "", "Pooya", "Kiri", "pkpkpk", UserState.FOLLOWING),
//            UserEntity(4, "", "Nibom", "Chaghal", "dalidali", UserState.REQUESTED)
//        )
        userRepository.getEventCheckins(_eventId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ checkins ->
                _users.value = checkins.map { it.userEntity }
            }, {
                Timber.v("Failed to get event checkins")
            })
            .also {
                compositeDisposable.add(it)
            }
    }

    fun setEventId(eventId: String) {
        _eventId = eventId
    }
}