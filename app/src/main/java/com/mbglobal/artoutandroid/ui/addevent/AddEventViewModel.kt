package com.mbglobal.artoutandroid.ui.addevent

import android.content.res.Resources
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventViewModel
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddEventViewModel @Inject constructor(private val eventRepository: EventRepository) :
    ManageEventViewModel() {

    private val _addedId = MutableLiveData<Int?>()
    val addedId: LiveData<Int?>
        get() = _addedId

    fun addEvent(eventEntity: AddEventEntity) {
        eventRepository.addEvent(eventEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { event ->
                    _addedId.value = event.id
                },
                { throwable ->
                    Log.v("OkHttp", "${throwable.message}")
                    println("error $throwable")
                    _addedId.value = null
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    fun setPageName(pageName: String) {
        pageNameText.value = pageName
    }

}