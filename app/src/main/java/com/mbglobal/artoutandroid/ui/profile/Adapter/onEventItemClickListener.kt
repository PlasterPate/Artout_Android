package com.mbglobal.artoutandroid.ui.profile.Adapter

import com.mbglobal.data.entity.event.EventEntity

interface onEventItemClickListener {
    fun onClicked(eventEntity: EventEntity)
}