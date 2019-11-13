package com.mbglobal.data.repository

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.datasource.UserLocalDataSource
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.LocationEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val  userLocalDataSource: UserLocalDataSource) {

    fun getEvent(eventId : Int) : Single<EventEntity> {
        return eventRemoteDataSource.getEvent(eventId)
    }

    fun addEvent(eventEntity : AddEventEntity) : Single<EventEntity> {
        return eventRemoteDataSource.addEvent(eventEntity)
    }

    fun getUserEvents(userId : String?) : Single<List<EventEntity>> {
        if (userId == null) {
            return userLocalDataSource.getUser().flatMap {userId ->
                eventRemoteDataSource.getUserEvents(userId.toInt())
            }
        } else {
            return eventRemoteDataSource.getUserEvents(userId.toInt())
        }
    }

}