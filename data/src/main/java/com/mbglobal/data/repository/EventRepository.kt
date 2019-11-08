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

    fun getEvent(eventId : String) : Single<EventEntity> {
        if (eventId == "1") {
            val image = "https://imgs.6sqft.com/wp-content/uploads/2019/10/02112923/Central-Park-fall-foliage-2019.jpg"
            val event = EventEntity("1",
                "پارک مرکزی",
                image,
                "پارک مرکزی نیویورک (در زبان انگلیسی: Central Park) نام پارک بزرگ و معروفی است که در مرکز منهتن و در میان مراکز تجاری شهر نیویورک قرار دارد. پارک مرکزی نیویورک، اولین پارک مُدرن در تاریخ ایالات متحدهٔ آمریکا محسوب می\u200Cشود که در سال ۱۸۵۷ میلادی توسط فردریک لاو المستد (Frederick Law Olmsted) و همکار انگلیسی اش کالورت واکس (Calvert Vaux) طراحی و افتتاح شد.\n" +
                        "\n" +
                        "وسعت این پارک حدود ۳۴۲ هکتار است که به شکل مستطیل در قلب منطقه منهتن (منطقه تجاری نیویورک) واقع شده\u200Cاست. همچنین این پارک دارای دریاچه\u200Cهایی به وسعت جمعاً ۶۰٫۷ هکتار می\u200Cباشد.\n" +
                        "\n" +
                        "سالیانه ۳۷ تا ۳۸ میلیون نفر از این پارک بازدید می\u200Cکنند",
                "2015","2016", "19:00", "20:00", 5.0, "پارک")
            return Single.just(event)
        }
        return eventRemoteDataSource.getEvent(eventId)
    }

    fun addEvent(eventEntity : EventEntity) : Completable {
        return eventRemoteDataSource.addEvent(eventEntity)
    }

    fun getUserEvents(userId : String?) : Observable<EventEntity> {
        val list = mutableListOf<EventEntity>()
        val image = "https://imgs.6sqft.com/wp-content/uploads/2019/10/02112923/Central-Park-fall-foliage-2019.jpg"
        val event = EventEntity("1",
            "پارک مرکزی",
            image,
            "پارک مرکزی نیویورک (در زبان انگلیسی: Central Park) نام پارک بزرگ و معروفی است که در مرکز منهتن و در میان مراکز تجاری شهر نیویورک قرار دارد. پارک مرکزی نیویورک، اولین پارک مُدرن در تاریخ ایالات متحدهٔ آمریکا محسوب می\u200Cشود که در سال ۱۸۵۷ میلادی توسط فردریک لاو المستد (Frederick Law Olmsted) و همکار انگلیسی اش کالورت واکس (Calvert Vaux) طراحی و افتتاح شد.\n" +
                    "\n" +
                    "وسعت این پارک حدود ۳۴۲ هکتار است که به شکل مستطیل در قلب منطقه منهتن (منطقه تجاری نیویورک) واقع شده\u200Cاست. همچنین این پارک دارای دریاچه\u200Cهایی به وسعت جمعاً ۶۰٫۷ هکتار می\u200Cباشد.\n" +
                    "\n" +
                    "سالیانه ۳۷ تا ۳۸ میلیون نفر از این پارک بازدید می\u200Cکنند",
            "2015","2016", "19:00", "20:00", 5.0, "پارک")
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