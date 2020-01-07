package com.mbglobal.data.datasource

import com.mbglobal.data.entity.checkin.CheckinEntity
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.EventSearchEntity
import io.reactivex.Completable
import io.reactivex.Single

interface EventRemoteDataSource {

    fun getEvent(eventId: Int): Single<EventEntity>

    fun addEvent(addEventEntity: AddEventEntity): Single<EventEntity>

    fun editEvent(eventId: Int, eventEntity: AddEventEntity): Single<EventEntity>

    fun getUserEvents(userId: String): Single<List<EventEntity>>

    fun getUserCheckIns(userId: String): Single<List<CheckinEntity>>

    fun checkin(eventEntity: EventEntity): Completable

    fun searchEvent(query: EventSearchEntity): Single<List<EventEntity>>
}
