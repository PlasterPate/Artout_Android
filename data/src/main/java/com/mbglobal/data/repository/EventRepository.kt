package com.mbglobal.data.repository

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.datasource.UserLocalDataSource
import com.mbglobal.data.entity.user.EventEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource) {

    fun getEvent(slug : String) : Single<EventEntity>{
        return eventRemoteDataSource.getEvent(slug)
    }

    fun addEvent(eventEntity : EventEntity) : Completable {
        return eventRemoteDataSource.addEvent(eventEntity)
    }

    fun getUserEvents(userId : String) : Single<List<String>> {
        return eventRemoteDataSource.getUserEvents(userId)
    }

}