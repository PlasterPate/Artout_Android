package com.mbglobal.artoutandroid.ui.events

import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.repository.EventRepository
import javax.inject.Inject

class EventsViewModel @Inject constructor(private val eventRepository: EventRepository) : BaseViewModel() {

}