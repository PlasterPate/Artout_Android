package com.mbglobal.artoutandroid.ui.manageevent

import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import javax.inject.Inject

abstract class ManageEventViewModel : BaseViewModel() {

    var pageNameText = MutableLiveData<String>()

//    fun validateEvent(eventEntity: EventEntity) : Boolean{
//
//    }
}