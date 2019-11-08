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
        val image = "https://imgs.6sqft.com/wp-content/uploads/2019/10/02112923/Central-Park-fall-foliage-2019.jpg"
        val event = EventEntity("1",
            "Central Perk",
            image,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Why do we use it?It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using \\'Content here, content here\\', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for \\'lorem ipsum\\' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)",
            "","", "", "", 5.0, "")
        for (i in 1..10){
            list.add(event)
        }
        return Observable.fromIterable(list)
//        val ids= if (userId == null){
//            userLocalDataSource.getUser().flatMap {
//                eventRemoteDataSource.getUserEvents(it)
//            }
//        }else{
//            eventRemoteDataSource.getUserEvents(userId)
//        }
//        return ids.toObservable()
//            .flatMap {
//                Observable.fromIterable(it)
//            }.flatMap {
//                eventRemoteDataSource.getEvent(it).toObservable()
//            }
    }

}