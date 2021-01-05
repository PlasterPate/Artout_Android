package com.mbglobal.data.repository

import com.mbglobal.data.datasource.EventLocalDataSource
import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.datasource.SessionLocalDataSource
import com.mbglobal.data.entity.checkin.CheckinEntity
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.EventSearchEntity
import com.mbglobal.data.entity.event.S3ResponseEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val eventLocalDataSource: EventLocalDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource
) {

    fun getEvent(eventId: Int): Single<EventEntity> {
        return MockEventFactory.getEvent()          // Mock code
        /*                                          // Correct code
        return eventLocalDataSource.getEvent(eventId).onErrorResumeNext {
            eventRemoteDataSource.getEvent(eventId)
        }
         */
    }

    fun addEvent(eventEntity: AddEventEntity): Single<EventEntity> {
        return sessionLocalDataSource.getUserId().flatMap {userId ->
            eventRemoteDataSource.addEvent(eventEntity.copy(owner = userId.toInt()))
                .flatMap {
//                eventEntity.image?.let {image ->
//                    println("Going to upload")
//                    //uploadImage("events",it.s3ResponseEntity,image)
//                }
                //eventLocalDataSource.addEvent(it.event)
                    Single.just(it.event)
            }
        }
    }

    fun editEvent(eventId: Int, eventEntity: AddEventEntity): Single<EventEntity> {
        return sessionLocalDataSource.getUserId().flatMap {userId ->
            println("repo edit")
            eventRemoteDataSource.editEvent(eventId, eventEntity.copy(owner = userId.toInt())).flatMap {
                eventLocalDataSource.editEvent(it)
            }
        }
    }

    fun getUserEvents(userId: String?): Single<List<EventEntity>> {
        return MockEventFactory.getEvents()             // Mock code
        /*                                              // Correct code
        val idSingle =
            userId?.let {
                Single.just(userId)
            } ?: sessionLocalDataSource.getUserId()
        return idSingle.flatMap { id -> eventRemoteDataSource.getUserEvents(id) }
         */
    }

    fun getUserCheckIns(userId: String?): Single<List<CheckinEntity>> {
        val idSingle =
            userId?.let {
                Single.just(it)
            } ?: sessionLocalDataSource.getUserId()
        return idSingle.flatMap { id -> eventRemoteDataSource.getUserCheckIns(id) }
    }

    fun checkin(eventEntity: EventEntity): Completable{
        return eventRemoteDataSource.checkin(eventEntity.id.toString())
    }

    fun checkout(eventId: String): Completable{
        return eventRemoteDataSource.checkout(eventId)
    }

    fun searchEvent(query: EventSearchEntity): Single<List<EventEntity>>{
        return eventRemoteDataSource.searchEvent(query)
    }

    fun uploadImage(url: String,s3ResponseEntity: S3ResponseEntity, imagePath: String){
        println("url : $url")
        println("path : $imagePath")
        eventRemoteDataSource.uploadImage(url,s3ResponseEntity, imagePath)
        println("uploaded")
    }
}