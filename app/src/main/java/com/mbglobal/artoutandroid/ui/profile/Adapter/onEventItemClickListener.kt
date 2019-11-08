package com.mbglobal.artoutandroid.ui.profile.Adapter

import com.mbglobal.data.entity.user.EventEntity

interface onEventItemClickListener {
    fun onClicked(eventEntity: EventEntity)
}