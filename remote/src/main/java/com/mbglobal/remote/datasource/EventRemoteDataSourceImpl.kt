package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.entity.user.EventEntity
import com.mbglobal.remote.api.EventService
import com.mbglobal.remote.dto.event.EventGetDto
import com.mbglobal.remote.mappers.toEventDto
import com.mbglobal.remote.mappers.toEventEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class EventRemoteDataSourceImpl @Inject constructor(private val eventService: EventService) :
    EventRemoteDataSource{

    override fun getEvent(eventId: String): Single<EventEntity> {
        return eventService.getEvent(EventGetDto(eventId)).map {
            it.toEventEntity()
        }
    }

    override fun addEvent(eventEntity: EventEntity): Completable {
        return eventService.addEvent(eventEntity.toEventDto())
    }

    override fun getUserEvents(userId: String): Single<List<String>> {
        return eventService.getUserEvents(userId)
    }
}