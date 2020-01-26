package com.mbglobal.artoutandroid.ui.eventdetails

import com.mbglobal.data.entity.event.EventEntity

interface OnCheckinListItemClickListener {
    fun onClicked(eventEntity: EventEntity)
}