package com.mbglobal.data.datasource

import com.mbglobal.data.entity.event.EventEntity
import io.reactivex.Completable
import io.reactivex.Single

interface EventRemoteDataSource {

    fun getEvent(eventId : Int) : Single<EventEntity>

    fun addEvent(eventEntity: EventEntity) : Single<EventEntity>

    fun getUserEvents(userId : Int) : Single<List<String>>
}