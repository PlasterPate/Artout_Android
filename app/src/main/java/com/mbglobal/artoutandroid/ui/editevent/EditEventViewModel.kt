package com.mbglobal.artoutandroid.ui.editevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventViewModel
import com.mbglobal.data.entity.event.EventEntity
import javax.inject.Inject

class EditEventViewModel @Inject constructor() : ManageEventViewModel() {

    var eventEntity = MutableLiveData<EventEntity>()

    
}