package com.mbglobal.data.datasource

import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import io.reactivex.Completable
import io.reactivex.Single

interface EventRemoteDataSource {

    fun getEvent(eventId : Int) : Single<EventEntity>

    fun addEvent(addEventEntity: AddEventEntity) : Single<EventEntity>

    fun editEvent(eventId: Int, eventEntity: AddEventEntity) : Single<EventEntity>

    fun getUserEvents() : Single<List<EventEntity>>

    fun getUserCheckIns(): Single<List<EventEntity>>

    fun getUserSuggestions(): Single<List<EventEntity>>
}
