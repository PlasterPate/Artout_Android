package com.mbglobal.artoutandroid.ui.eventdetails


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.user.EventEntity
import com.mbglobal.data.repository.EventRepository
import javax.inject.Inject

class EventDetailsViewModel @Inject constructor(private val eventRepository: EventRepository) : BaseViewModel() {

    private var _eventEntity = MutableLiveData<EventEntity>()
    val eventEntity : LiveData<EventEntity>
        get() = _eventEntity
}