package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.TimelineRemoteDataSource
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.remote.api.TimelineService
import com.mbglobal.remote.mappers.toEventEntity
import io.reactivex.Single
import javax.inject.Inject

class TimelineRemoteDataSourceImpl @Inject constructor(private val timelineService: TimelineService) : TimelineRemoteDataSource {

    override fun getTimelineItems(): Single<List<EventEntity>> {
        return timelineService.getTimelineItems().map { eventDtos ->
            eventDtos.map {
                it.toEventEntity()
            }
        }
    }
}