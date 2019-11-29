package com.mbglobal.data.repository

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import io.reactivex.Single
import java.awt.Event
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val  sessionLocalDataSource: SessionLocalDataSource) {

    fun getEvent(eventId: Int): Single<EventEntity> {
        return eventRemoteDataSource.getEvent(eventId)
    }

    fun addEvent(eventEntity: AddEventEntity): Single<EventEntity> {
        return eventRemoteDataSource.addEvent(eventEntity)
    }

    fun getUserEvents(userId : String?) : Single<List<EventEntity>> {
        return if (userId == null) {
            sessionLocalDataSource.getSession()
                .map { session -> session.userId }
                .flatMap { id -> eventRemoteDataSource.getUserEvents(id.toInt()) }
        } else {
            eventRemoteDataSource.getUserEvents(userId.toInt())
        }
    }

    fun getUserCheckIns(userId: String?): Single<List<EventEntity>> {
        val idSingle =
            userId?.let {
                Single.just(it)
            } ?: userLocalDataSource.getUser()
        return idSingle.flatMap {
            eventRemoteDataSource.getUserCheckIns(it.toInt())
        }
    }

    fun getUserSuggestions(userId: String?): Single<List<EventEntity>> {
        val idSingle =
            userId?.let {
                Single.just(it)
            } ?: userLocalDataSource.getUser()
        return idSingle.flatMap {
            eventRemoteDataSource.getUserSuggestions(it.toInt())
        }
    }

    fun editEvent(eventId: Int, eventEntity: AddEventEntity): Single<EventEntity> {
        return eventRemoteDataSource.editEvent(eventId, eventEntity)
    }
}