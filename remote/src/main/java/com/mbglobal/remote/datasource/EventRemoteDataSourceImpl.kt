package com.mbglobal.remote.datasource

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.entity.checkin.CheckinEntity
import com.mbglobal.data.entity.event.*
import com.mbglobal.remote.api.EventService
import com.mbglobal.remote.api.S3Service
import com.mbglobal.remote.dto.checkin.AddCheckinDto
import com.mbglobal.remote.dto.event.S3FieldsDto
import com.mbglobal.remote.mappers.*
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import java.io.File
import java.net.URI
import javax.inject.Inject

class EventRemoteDataSourceImpl @Inject constructor(
    private val eventService: EventService,
    private val s3Service: S3Service) :
    EventRemoteDataSource {
    override fun getEvent(eventId: Int): Single<EventEntity> {
        return eventService.getEvent(eventId).map {
            it.toEventEntity()
        }
    }

    override fun addEvent(addEventEntity: AddEventEntity): Single<AddEventResponseEntity> {
        return eventService.addEvent(addEventEntity.toAddEventDto()).map {
            it.toAddEventResponseEntity()
        }
    }

    override fun editEvent(eventId: Int, eventEntity: AddEventEntity): Single<EventEntity> {
        return eventService.editEvent(eventId, eventEntity.toAddEventDto()).map {
            it.toEventEntity()
        }
    }

    override fun getUserEvents(userId: String): Single<List<EventEntity>> {
        return eventService.getUserEvents(userId).map { events ->
            events.map { eventDto ->
                eventDto.toEventEntity()
            }
        }
    }

    override fun getUserCheckIns(userId: String): Single<List<CheckinEntity>> {
        return eventService.getUserCheckIns(userId).map { checkins ->
            checkins.map { checkinDto ->
                checkinDto.toCheckinEntity()
            }
        }
    }

    override fun checkin(eventId: String): Completable {
        return Completable.fromSingle(eventService.checkin( AddCheckinDto(eventId)))
    }

    override fun checkout(eventId: String): Completable {
        return Completable.fromSingle(eventService.checkout(eventId))
    }

    override fun searchEvent(query: EventSearchEntity): Single<List<EventEntity>> {
        return eventService.searchEvent(query.toQueryMap()).map { events ->
            events.map { eventDto ->
                eventDto.toEventEntity()
            }
        }
    }

    override fun uploadImage(url: String, s3ResponseEntity: S3ResponseEntity, imagePath: String): Single<ResponseBody> {
        val mediaType = "multipart/form-data".toMediaType()
        val imageFile = File(imagePath)
        val reqBody = MultipartBody.Part.createFormData("image","image",imageFile.toString().toRequestBody(mediaType))
        println("In DS")
        println(mediaType)
        println(imageFile)
        println(reqBody)
        eventService.getEvent(15)
        return s3Service.uploadImage(url, s3ResponseEntity.toS3ResponseDto().s3FieldsDto, reqBody)
    }
}


