package com.mbglobal.artoutandroid.ui.discover

import com.mbglobal.artoutandroid.ui.base.BaseViewModel
import com.mbglobal.data.entity.event.EventSearchEntity
import com.mbglobal.data.repository.EventRepository
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(private val eventRepository: EventRepository) : BaseViewModel() {

    fun onQueryChange(searchQuery: String) {
        eventRepository.searchEvent(
            EventSearchEntity(
                event = searchQuery,
                category = null
            )
        )
    }
}
