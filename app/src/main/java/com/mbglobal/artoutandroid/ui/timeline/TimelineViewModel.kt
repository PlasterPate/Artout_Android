package com.mbglobal.artoutandroid.ui.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.repository.TimelineRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TimelineViewModel @Inject constructor(
    private val timelineRepository: TimelineRepository
) : BaseViewModel() {

    private val _timelineItems: MutableLiveData<List<EventEntity>> = MutableLiveData()
    val timelineItems: LiveData<List<EventEntity>> = _timelineItems

    fun loadTimeline() {
        timelineRepository.getTimelineItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _timelineItems.postValue(it)
            }, {

            }).also {
                compositeDisposable.add(it)
            }
    }

}