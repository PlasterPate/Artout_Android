package com.mbglobal.artoutandroid.ui.eventdetails.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.data.entity.event.EventEntity

class EventDescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val descriptionTextView = itemView.findViewById<TextView>(R.id.tv_event_detail_description)

    fun bind(eventEntity: EventEntity) {
        descriptionTextView.text = eventEntity.description
    }

}