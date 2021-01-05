package com.mbglobal.data.repository

import com.mbglobal.data.datasource.TimelineRemoteDataSource
import com.mbglobal.data.entity.event.EventEntity
import io.reactivex.Single
import javax.inject.Inject

class TimelineRepository @Inject constructor(private val timelineRemoteDataSource: TimelineRemoteDataSource) {

    fun getTimelineItems(pageNumber: Int): Single<List<EventEntity>> {
        return MockEventFactory.getEvents()                                      // Mock code
//        return timelineRemoteDataSource.getTimelineItems(pageNumber)      // Correct code
    }
}