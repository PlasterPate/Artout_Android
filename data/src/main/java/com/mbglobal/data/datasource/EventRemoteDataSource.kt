package com.mbglobal.data.datasource

import com.mbglobal.data.entity.user.EventEntity
import io.reactivex.Completable
import io.reactivex.Single

interface EventRemoteDataSource {

    fun getEvent(slug : String) : Single<EventEntity>

    fun addEvent(eventEntity: EventEntity) : Completable

    fun getUserEvents(userId : String) : Single<List<String>>
}