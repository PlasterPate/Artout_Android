package com.mbglobal.artoutandroid.ui.eventlist.adapter

import com.mbglobal.data.entity.event.EventEntity

interface OnEventItemClickListener {

    fun onClicked(eventEntity: EventEntity)

}