package com.mbglobal.artoutandroid.ui.addevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddEventViewModel @Inject constructor(private val eventRepository: EventRepository) : ManageEventViewModel() {

    
    private val _addStatus = MutableLiveData<Boolean>()
    val addStatus : LiveData<Boolean>
        get() = _addStatus

    fun addEvent(eventEntity: EventEntity){
        eventRepository.addEvent(eventEntity)
    }
}