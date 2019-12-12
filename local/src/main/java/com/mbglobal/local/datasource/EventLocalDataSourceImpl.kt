package com.mbglobal.local.datasource

import com.mbglobal.data.datasource.EventLocalDataSource
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.local.database.LocalDatabaseDao
import io.reactivex.Single
import javax.inject.Inject
import com.mbglobal.local.mappers.*

class EventLocalDataSourceImpl @Inject constructor(private val databaseDao: LocalDatabaseDao) :
    EventLocalDataSource {
    override fun getEvent(eventId: Int): Single<EventEntity> {
        return databaseDao.get(eventId).map {
            it.toEventEntity()
        }
    }

    override fun addEvent(eventEntity: EventEntity): Single<EventEntity> {
        return databaseDao.insert(eventEntity.toEventTable()).andThen(Single.just(eventEntity))
    }

    override fun editEvent(eventEntity: EventEntity): Single<EventEntity> {
        return databaseDao.update(eventEntity.toEventTable()).andThen(Single.just(eventEntity))
    }
}