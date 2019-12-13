package com.mbglobal.local.datasource

import android.content.Context
import androidx.core.net.toUri
import com.mbglobal.data.datasource.EventLocalDataSource
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.local.FileCreator
import com.mbglobal.local.database.LocalDatabaseDao
import io.reactivex.Single
import javax.inject.Inject
import com.mbglobal.local.mappers.*

class EventLocalDataSourceImpl @Inject constructor(
    private val databaseDao: LocalDatabaseDao,
    private val context: Context
) :
    EventLocalDataSource {
    override fun getEvent(eventId: Int): Single<EventEntity> {
        return databaseDao.get(eventId).map {
            it.toEventEntity()
        }
    }

    override fun addEvent(eventEntity: EventEntity): Single<EventEntity> {
        val imagePath = FileCreator.saveImageFile(eventEntity.image?.toUri(), context)
        val newEventEntity = eventEntity.copy(image = imagePath)
        return databaseDao.insert(newEventEntity.toEventTable())
            .andThen(Single.just(newEventEntity))
    }

    override fun editEvent(eventEntity: EventEntity): Single<EventEntity> {
        return databaseDao.update(eventEntity.toEventTable()).andThen(Single.just(eventEntity))
    }

    override fun getUserEvents(): Single<List<EventEntity>> {
        return databaseDao.getAllEvents().map {events ->
            events.map {eventTable ->
                eventTable.toEventEntity()
            }
        }
    }
}