package com.mbglobal.artoutandroid.ui.eventdetails.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.data.entity.user.EventEntity

class EventTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val startTimeTextView = itemView.findViewById<TextView>(R.id.tv_event_detail_start_time)
    val finishTimeTextView = itemView.findViewById<TextView>(R.id.tv_event_detail_finish_time)

    fun bind(eventEntity: EventEntity) {
        startTimeTextView.text = eventEntity.startDate
        finishTimeTextView.text = eventEntity.endDate
    }
    
}