package com.mbglobal.artoutandroid.ui.addevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddEventViewModel @Inject constructor(private val eventRepository: EventRepository) : ManageEventViewModel() {

    
    private val _addedId = MutableLiveData<Int?>()
    val addedId : LiveData<Int?>
        get() = _addedId

    fun addEvent(eventEntity: EventEntity){
        eventRepository.addEvent(eventEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {event ->
                    _addedId.value = event.id
                },
                { throwable ->
                    _addedId.value = null
                }
            ).also {
                compositeDisposable.add(it)
            }
    }
}