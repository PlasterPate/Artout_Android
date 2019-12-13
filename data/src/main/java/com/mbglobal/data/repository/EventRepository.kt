package com.mbglobal.data.repository

import com.mbglobal.data.datasource.EventLocalDataSource
import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
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
        return eventRemoteDataSource.addEvent(eventEntity).flatMap {
            eventLocalDataSource.addEvent(it)
        }
    }

    fun getUserEvents(userId: String?): Single<List<EventEntity>> {
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMap { id -> eventRemoteDataSource.getUserEvents(id.toInt()) }
    }

    fun getUserCheckIns(userId: String?): Single<List<EventEntity>> {
        val idSingle =
            userId?.let {
                Single.just(it)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMap { eventRemoteDataSource.getUserCheckIns(it.toInt()) }
    }

    fun getUserSuggestions(userId: String?): Single<List<EventEntity>> {
        val idSingle =
            userId?.let {
                Single.just(it)
            } ?: sessionLocalDataSource.getUser()
        return idSingle.flatMap {
            eventRemoteDataSource.getUserSuggestions(it.toInt())
        }
    }

    fun editEvent(eventId: Int, eventEntity: AddEventEntity): Single<EventEntity> {
        return eventRemoteDataSource.editEvent(eventId, eventEntity).flatMap {
            eventLocalDataSource.editEvent(it)
        }
    }
}