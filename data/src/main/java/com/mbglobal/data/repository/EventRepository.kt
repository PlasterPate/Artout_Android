package com.mbglobal.data.repository

import com.mbglobal.data.datasource.EventLocalDataSource
import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.EventSearchEntity
import io.reactivex.Single
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val eventLocalDataSource: EventLocalDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource
) {

    fun getEvent(eventId: Int): Single<EventEntity> {
        return eventLocalDataSource.getEvent(eventId).onErrorResumeNext {
            eventRemoteDataSource.getEvent(eventId)
        }
    }

    fun addEvent(eventEntity: AddEventEntity): Single<EventEntity> {
        return sessionLocalDataSource.getUserId().flatMap {userId ->
            eventRemoteDataSource.addEvent(eventEntity.copy(owner = userId.toInt())).flatMap {
                eventLocalDataSource.addEvent(it)
            }
        }
    }

    fun editEvent(eventId: Int, eventEntity: AddEventEntity): Single<EventEntity> {
        return sessionLocalDataSource.getUserId().flatMap {userId ->
            eventRemoteDataSource.editEvent(eventId, eventEntity.copy(owner = userId.toInt())).flatMap {
                eventLocalDataSource.editEvent(it)
            }
        }
    }

    fun getUserEvents(userId: String?): Single<List<EventEntity>> {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUserId()
        return idSingle.flatMap { id -> eventLocalDataSource.getUserEvents() }
    }

    fun getUserCheckIns(userId: String?): Single<List<EventEntity>> {
        val idSingle =
            userId?.let {
                Single.just(it)
            } ?: sessionLocalDataSource.getUserId()
        return idSingle.flatMap { eventRemoteDataSource.getUserCheckIns() }
    }

    fun getUserSuggestions(userId: String?): Single<List<EventEntity>> {
        val idSingle =
            userId?.let {
                Single.just(it)
            } ?: sessionLocalDataSource.getUserId()
        return idSingle.flatMap {
            eventRemoteDataSource.getUserSuggestions()
        }
    }

    fun searchEvent(query: EventSearchEntity): Single<List<EventEntity>>{
        return Single.just(listOf(MockEventFactory.COLDPLAY_CONCERT, MockEventFactory.COLDPLAY_CONCERT2, MockEventFactory.COLDPLAY_CONCERT3))
        return eventRemoteDataSource.searchEvent(query)
    }
}