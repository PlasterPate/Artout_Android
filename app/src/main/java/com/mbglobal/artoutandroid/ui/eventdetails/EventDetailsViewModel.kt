package com.mbglobal.artoutandroid.ui.eventdetails


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventDetailsViewModel @Inject constructor(private val eventRepository: EventRepository) : BaseViewModel() {

    private val _eventEntity = MutableLiveData<EventEntity>()
    val eventEntity: LiveData<EventEntity> = _eventEntity

    private val _eventLoadError = MutableLiveData<String>()
    val eventLoadError: LiveData<String> = _eventLoadError

    fun loadEvent(eventId: Int) {

        if (eventId == 0) {
            _eventLoadError.value = "Invalid event"
        }

        eventRepository.getEvent(eventId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _eventEntity.value = it
            }, {
                _eventLoadError.value = "Failed to load event"
            }).also {
                compositeDisposable.add(it)
            }

    }
}