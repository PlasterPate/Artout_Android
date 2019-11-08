package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.remote.api.EventService
import com.mbglobal.remote.dto.event.EventGetDto
import com.mbglobal.remote.mappers.toAddEventDto
import com.mbglobal.remote.mappers.toEventEntity
import io.reactivex.Single
import javax.inject.Inject

class EventRemoteDataSourceImpl @Inject constructor(private val eventService: EventService) :
    EventRemoteDataSource{

    override fun getEvent(eventId: Int): Single<EventEntity> {
        return eventService.getEvent(eventId).map {
            it.toEventEntity()
        }
    }

    override fun addEvent(eventEntity: EventEntity): Single<EventEntity> {
        return eventService.addEvent(eventEntity.toAddEventDto()).map {
            it -> it.toEventEntity()
        }
    }

    override fun getUserEvents(userId: Int): Single<List<String>> {
        return eventService.getUserEvents(userId)
    }
}

