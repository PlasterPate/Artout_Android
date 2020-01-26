package com.mbglobal.artoutandroid.ui.eventdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.eventdetails.viewholder.*
import com.mbglobal.data.entity.event.EventEntity

class EventDetailsAdapter(private val eventEntity: EventEntity,
                          private val onCheckinListItemClickListener: OnCheckinListItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_TYPE_COVER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_cover_item, parent, false)
                return CoverViewHolder(view)
            }
            ITEM_TYPE_EVENT_INFO -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_detail_event_info, parent, false)
                return EventInfoViewHolder(view)
            }
            ITEM_TYPE_EVENT_CHECKINS -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_detail_event_checkins, parent, false)
                return EventCheckinsViewHolder(view)
            }
            ITEM_TYPE_EVENT_DESCRIPTION -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_detail_event_description, parent, false)
                return EventDescriptionViewHolder(view)
            }
            ITEM_TYPE_TIME -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_event_time, parent, false)
                return EventTimeViewHolder(view)
            }
            else -> {
                throw Exception("Invalid view type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_TYPE_COVER
            1 -> ITEM_TYPE_EVENT_INFO
            2 -> ITEM_TYPE_EVENT_CHECKINS
            3 -> ITEM_TYPE_EVENT_DESCRIPTION
            4 -> ITEM_TYPE_TIME
            else -> throw Exception("Invalid position")
        }
    }

    override fun getItemCount() = 5

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            ITEM_TYPE_COVER -> (holder as CoverViewHolder).bind(eventEntity)
            ITEM_TYPE_EVENT_INFO -> (holder as EventInfoViewHolder).bind(eventEntity)
            ITEM_TYPE_EVENT_CHECKINS -> (holder as EventCheckinsViewHolder).bind(eventEntity,
                onCheckinListItemClickListener)
            ITEM_TYPE_EVENT_DESCRIPTION -> (holder as EventDescriptionViewHolder).bind(eventEntity)
            ITEM_TYPE_TIME -> (holder as EventTimeViewHolder).bind(eventEntity)
        }
    }

    companion object {
        const val ITEM_TYPE_COVER = 100
        const val ITEM_TYPE_EVENT_INFO = 200
        const val ITEM_TYPE_EVENT_CHECKINS = 300
        const val ITEM_TYPE_EVENT_DESCRIPTION = 400
        const val ITEM_TYPE_TIME = 500
    }

}