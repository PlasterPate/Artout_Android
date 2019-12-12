package com.mbglobal.data.datasource

import com.mbglobal.data.entity.event.EventEntity
import io.reactivex.Single

interface EventLocalDataSource {

    fun getEvent(eventId: Int): Single<EventEntity>

    fun addEvent(eventEntity: EventEntity): Single<EventEntity>

    fun editEvent(eventEntity: EventEntity): Single<EventEntity>
}