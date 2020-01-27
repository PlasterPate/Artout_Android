package com.mbglobal.artoutandroid.ui.eventdetails


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.app.LiveEvent
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.EventRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class EventDetailsViewModel @Inject constructor(private val eventRepository: EventRepository) :
    BaseViewModel() {

    private val _eventEntity = MutableLiveData<EventEntity>()
    val eventEntity: LiveData<EventEntity> = _eventEntity

    private val _eventLoadError = MutableLiveData<String>()
    val eventLoadError: LiveData<String> = _eventLoadError

    private val _checkinStatus = MutableLiveData<LiveEvent<String>>()
    val checkinStatus: LiveData<LiveEvent<String>> = _checkinStatus

    fun loadEvent(eventId: Int) {

        if (eventId == 0) {
            _eventLoadError.value = "Invalid event"
        }
        println("hey")
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

    fun switchCheckinState() {
        if (_eventEntity.value?.checkinState == true){
            checkout()
        }else{
            checkin()
        }
        println(_eventEntity.value!!.id)

    }

    private fun checkin(){
        eventRepository.checkin(_eventEntity.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _checkinStatus.value = LiveEvent("You Checked In this Event")
                loadEvent(_eventEntity.value!!.id)
            },{
                Timber.e("Failed to checkin")
            })
            .also {
                compositeDisposable.add(it)
            }
    }

    private fun checkout(){
        eventRepository.checkout(_eventEntity.value?.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _checkinStatus.value = LiveEvent("You Checked Out this Event")
                loadEvent(_eventEntity.value!!.id)
            },{
                Timber.e("Failed to checkout")
            })
            .also {
                compositeDisposable.add(it)
            }
    }
}