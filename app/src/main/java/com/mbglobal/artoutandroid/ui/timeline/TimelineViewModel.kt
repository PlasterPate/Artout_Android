package com.mbglobal.artoutandroid.ui.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.TimelineRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class TimelineViewModel @Inject constructor(
    private val timelineRepository: TimelineRepository
) : BaseViewModel() {

    private val _timelineItems: MutableLiveData<List<EventEntity>> = MutableLiveData()
    val timelineItems: LiveData<List<EventEntity>> = _timelineItems

    private val _showProgress: MutableLiveData<Boolean> = MutableLiveData()
    val showProgress: LiveData<Boolean> = _showProgress

    private var pageCounter = 1

    fun loadTimeline() {
        _showProgress.value = true
        timelineRepository.getTimelineItems(pageCounter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterSuccess {
                pageCounter += 1
            }
            .subscribe({
                val newItems = (_timelineItems.value?: mutableListOf()) + it
                _timelineItems.postValue(newItems)
            }, {

            }).also {
                compositeDisposable.add(it)
            }
    }

}