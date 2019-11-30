package com.mbglobal.artoutandroid.ui.eventlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventListViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : BaseViewModel() {

    private val _userEvents: MutableLiveData<List<EventEntity>> = MutableLiveData()
    val userEvents: LiveData<List<EventEntity>> = _userEvents

    fun getUserEvents(userId: String?) {
        eventRepository.getUserEvents(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ events ->
                _userEvents.value = events
            }, {
                println(it)
            }).also {
                compositeDisposable.add(it)
            }
    }

}