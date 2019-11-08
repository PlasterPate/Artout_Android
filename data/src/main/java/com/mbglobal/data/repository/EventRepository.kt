package com.mbglobal.data.repository

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.datasource.UserLocalDataSource
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.event.LocationEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val  userLocalDataSource: UserLocalDataSource) {

    fun getEvent(eventId : Int) : Single<EventEntity> {
//        if (eventId == 1) {
//            val image = "https://imgs.6sqft.com/wp-content/uploads/2019/10/02112923/Central-Park-fall-foliage-2019.jpg"
//            val location = LocationEntity(
//                12.0,
//                10.0
//            )
//
//            val event = EventEntity(
//                "This is the event description",
//                "2019-01-01",
//                1,
//                location,
//                "https://imgs.6sqft.com/wp-content/uploads/2019/10/02112923/Central-Park-fall-foliage-2019.jpg",
//                "2020-01-02",
//                "Title",
//                "Concerts",
//                1
//            )
//            return Single.just(event)
//        }
        return eventRemoteDataSource.getEvent(eventId)
    }

    fun addEvent(eventEntity : EventEntity) : Single<EventEntity> {
        return eventRemoteDataSource.addEvent(eventEntity)
    }

    fun getUserEvents(userId : String?) : Observable<EventEntity> {
//        val list = mutableListOf<EventEntity>()
//        val image = "https://imgs.6sqft.com/wp-content/uploads/2019/10/02112923/Central-Park-fall-foliage-2019.jpg"
//        val location = LocationEntity(
//            12.0,
//            10.0
//        )
//
//        val event = EventEntity(
//            "This is the event description",
//            "2019-01-01",
//            1,
//            location,
//            "https://imgs.6sqft.com/wp-content/uploads/2019/10/02112923/Central-Park-fall-foliage-2019.jpg",
//            "2020-01-02",
//            "Title",
//            "Concerts",
//            1
//        )
//        for (i in 1..10){
//            list.add(event)
//        }
//        return Observable.fromIterable(list)
        val ids= if (userId == null){
            userLocalDataSource.getUser().flatMap {
                eventRemoteDataSource.getUserEvents(it.toInt())
            }
        }else{
            eventRemoteDataSource.getUserEvents(userId.toInt())
        }
        return ids.toObservable()
            .flatMap {
                Observable.fromIterable(it)
            }.flatMap {
                eventRemoteDataSource.getEvent(it.toInt()).toObservable()
            }
    }

}