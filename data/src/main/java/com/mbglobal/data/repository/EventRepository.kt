package com.mbglobal.data.repository

import com.mbglobal.data.datasource.EventRemoteDataSource
import com.mbglobal.data.datasource.UserLocalDataSource
import com.mbglobal.data.entity.user.EventEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val  userLocalDataSource: UserLocalDataSource) {

    fun getEvent(slug : String) : Single<EventEntity>{
        return eventRemoteDataSource.getEvent(slug)
    }

    fun addEvent(eventEntity : EventEntity) : Completable {
        return eventRemoteDataSource.addEvent(eventEntity)
    }

    fun getUserEvents(userId : String?) : Observable<EventEntity> {
        val list = mutableListOf<EventEntity>()
        val image = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
        val event = EventEntity("1",
            "Cafe Bazdid",
            listOf(image),
            "abcdefgh",
            "","", "", "", 5.0, "")
        for (i in 1..10){
            list.add(event)
        }
        return Observable.fromIterable(list)
        val ids= if (userId == null){
            userLocalDataSource.getUser().flatMap {
                eventRemoteDataSource.getUserEvents(it)
            }
        }else{
            eventRemoteDataSource.getUserEvents(userId)
        }
        return ids.toObservable()
            .flatMap {
                Observable.fromIterable(it)
            }.flatMap {
                eventRemoteDataSource.getEvent(it).toObservable()
            }
    }

}