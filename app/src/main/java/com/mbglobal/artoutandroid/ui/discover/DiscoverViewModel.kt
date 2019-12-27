package com.mbglobal.artoutandroid.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.EventSearchEntity
import com.mbglobal.data.entity.user.UserEntity
import com.mbglobal.data.entity.user.UserSearchEntity
import com.mbglobal.data.repository.EventRepository
import com.mbglobal.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository
) :
    BaseViewModel() {

    private val _users: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val users: LiveData<List<UserEntity>> = _users

    private val _events: MutableLiveData<List<EventEntity>> = MutableLiveData()
    val events: LiveData<List<EventEntity>> = _events

    fun onQueryChange(searchQuery: String) {
        if (searchQuery.isBlank()) {
            return
        }
//        eventRepository.searchEvent(
//            EventSearchEntity(
//                event = searchQuery,
//                category = null
//            )
//        ).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ events ->
//
//            }, {
//
//            }).also {
//                compositeDisposable.add(it)
//            }
        userRepository.searchUser(
            UserSearchEntity(
                searchQuery
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                _users.value = users
            }, {

            }).also {
                compositeDisposable.add(it)
            }
    }
}
