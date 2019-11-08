package com.mbglobal.artoutandroid.ui.eventdetails.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.data.entity.event.EventEntity

class EventInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleTextView = itemView.findViewById<TextView>(R.id.tv_event_detail_title)
    val categoryTextView = itemView.findViewById<TextView>(R.id.tv_event_detail_category)

    fun bind(eventEntity: EventEntity) {
        titleTextView.text = eventEntity.title
        categoryTextView.text = eventEntity.category
    }

}