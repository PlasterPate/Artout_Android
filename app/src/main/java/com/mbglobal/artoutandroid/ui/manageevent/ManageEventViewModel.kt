package com.mbglobal.artoutandroid.ui.manageevent

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import javax.inject.Inject

abstract class ManageEventViewModel : BaseViewModel() {

    private val _eventImage = MutableLiveData<String?>()
    val eventImage: LiveData<String?>
        get() = _eventImage

    var pageNameText = MutableLiveData<String>()

    fun setImage(imageUri: Uri?) {
        _eventImage.value = imageUri.toString()
    }
//    fun validateEvent(eventEntity: EventEntity) : Boolean{
//
//    }
}