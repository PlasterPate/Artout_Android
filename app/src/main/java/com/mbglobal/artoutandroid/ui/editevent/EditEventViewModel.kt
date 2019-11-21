package com.mbglobal.artoutandroid.ui.editevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventViewModel
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditEventViewModel @Inject constructor(private val eventRepository: EventRepository) : ManageEventViewModel() {

    var eventEntity = MutableLiveData<EventEntity>()

    private val _addedId = MutableLiveData<Int?>()
    val addedId : LiveData<Int?>
        get() = _addedId

    fun editEvent(eventId : Int, eventEntity: AddEventEntity){
        eventRepository.editEvent(eventId, eventEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _addedId.value = it.id
            },{
                _addedId.value = null
            }).also {
                compositeDisposable.add(it)
            }
    }

    fun loadEvent(eventId: Int){
        eventRepository.getEvent(eventId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                eventEntity.value = it
            },{
               eventEntity.value = null
            }).also {
                compositeDisposable.add(it)
            }
    }
}