package com.mbglobal.artoutandroid.ui.eventdetails.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.eventdetails.OnCheckinListItemClickListener
import com.mbglobal.data.entity.event.EventEntity

class EventCheckinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val checkinsTextView = itemView.findViewById<TextView>(R.id.tv_event_detail_checkins)

    fun bind(eventEntity: EventEntity, clickListener: OnCheckinListItemClickListener) {
        checkinsTextView.text = "32 People have checked in this event"
        itemView.setOnClickListener{
            clickListener.onClicked(eventEntity)
        }
    }
}