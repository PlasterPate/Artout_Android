package com.mbglobal.artoutandroid.ui.timeline

import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.repository.TimelineRepository
import javax.inject.Inject

class TimelineViewModel @Inject constructor(
    private val timelineRepository: TimelineRepository
) : BaseViewModel() {

    fun loadTimeline() {

    }

}