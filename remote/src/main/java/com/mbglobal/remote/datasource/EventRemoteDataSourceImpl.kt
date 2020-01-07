package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.entity.checkin.CheckinEntity
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.EventSearchEntity
import com.mbglobal.remote.api.EventService
import com.mbglobal.remote.mappers.*
import io.reactivex.Completable
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

    override fun getUserEvents(userId: String): Single<List<EventEntity>> {
        return eventService.getUserEvents(userId).map { events ->
            events.map { eventDto ->
                eventDto.toEventEntity()
            }
        }
    }

    override fun getUserCheckIns(userId: String): Single<List<CheckinEntity>> {
        return eventService.getUserCheckIns(userId).map { checkins ->
            checkins.map { checkinDto ->
                checkinDto.toCheckinEntity()
            }
        }
    }

    override fun checkin(eventEntity: EventEntity): Completable {
        return Completable.fromSingle(eventService.checkin(eventEntity.toAddCheckinDto()))
    }

    override fun checkout(eventId: String): Completable {
        return Completable.fromSingle(eventService.checkout(eventId))
    }

    override fun searchEvent(query: EventSearchEntity): Single<List<EventEntity>> {
        return eventService.searchEvent(query.toQueryMap()).map { events ->
            events.map { eventDto ->
                eventDto.toEventEntity()
            }
        }
    }
}


