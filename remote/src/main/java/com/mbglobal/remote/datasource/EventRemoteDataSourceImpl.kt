package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.EventSearchEntity
import com.mbglobal.remote.api.EventService
import com.mbglobal.remote.mappers.toAddEventDto
import com.mbglobal.remote.mappers.toEventEntity
import com.mbglobal.remote.mappers.toQueryMap
import io.reactivex.Single
import javax.inject.Inject

class EventRemoteDataSourceImpl @Inject constructor(private val eventService: EventService) :
    EventRemoteDataSource {
    override fun getEvent(eventId: Int): Single<EventEntity> {
        return eventService.getEvent(eventId).map {
            it.toEventEntity()
        }
    }

    override fun addEvent(addEventEntity: AddEventEntity): Single<EventEntity> {
        return eventService.addEvent(addEventEntity.toAddEventDto()).map {
            it.toEventEntity()
        }
    }

    override fun editEvent(eventId: Int, eventEntity: AddEventEntity): Single<EventEntity> {
        return eventService.editEvent(eventId, eventEntity.toAddEventDto()).map {
            it.toEventEntity()
        }
    }

    override fun getUserEvents(): Single<List<EventEntity>> {
        return eventService.getUserEvents().map { events ->
            events.map { eventDto ->
                eventDto.toEventEntity()
            }
        }
    }

    override fun getUserCheckIns(): Single<List<EventEntity>> {
        return eventService.getUserCheckIns().map { events ->
            events.map { eventDto ->
                eventDto.toEventEntity()
            }
        }
    }

    override fun getUserSuggestions(): Single<List<EventEntity>> {
        return eventService.getUserSuggestions().map { events ->
            events.map { eventDto ->
                eventDto.toEventEntity()
            }
        }
    }

    override fun searchEvent(query: EventSearchEntity): Single<List<EventEntity>> {
        return eventService.searchEvent(query.toQueryMap())
    }
}


