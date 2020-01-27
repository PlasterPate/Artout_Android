package com.mbglobal.data.datasource

import com.mbglobal.data.entity.checkin.CheckinEntity
import com.mbglobal.data.entity.event.*
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody

interface EventRemoteDataSource {

    fun getEvent(eventId: Int): Single<EventEntity>

    fun addEvent(addEventEntity: AddEventEntity): Single<AddEventResponseEntity>

    fun editEvent(eventId: Int, eventEntity: AddEventEntity): Single<EventEntity>

    fun getUserEvents(userId: String): Single<List<EventEntity>>

    fun getUserCheckIns(userId: String): Single<List<CheckinEntity>>

    fun checkin(eventId: String): Completable

    fun checkout(eventId: String): Completable

    fun searchEvent(query: EventSearchEntity): Single<List<EventEntity>>

    fun uploadImage(url: String, s3ResponseEntity: S3ResponseEntity, imagePath: String): Single<ResponseBody>
}
