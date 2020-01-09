package com.mbglobal.data.datasource

import com.mbglobal.data.entity.event.EventEntity
import io.reactivex.Single

interface TimelineRemoteDataSource {

    fun getTimelineItems(): Single<List<EventEntity>>
}