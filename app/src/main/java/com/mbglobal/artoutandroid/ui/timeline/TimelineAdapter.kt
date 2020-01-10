package com.mbglobal.artoutandroid.ui.timeline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.eventlist.adapter.OnEventItemClickListener
import com.mbglobal.data.entity.event.EventEntity
import com.squareup.picasso.Picasso

class TimelineAdapter(private val onEventItemClickListener: OnEventItemClickListener) : RecyclerView.Adapter<TimelineAdapter.EventViewHolder>() {

    var items = mutableListOf<EventEntity>()

    fun setData(events: List<EventEntity>) {
        items.clear()
        items.addAll(events)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_event, parent, false)

        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(items[position], onEventItemClickListener)
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val eventImage: ImageView = itemView.findViewById(R.id.image_item)
        private val eventTitle: TextView = itemView.findViewById(R.id.title_item)
        private val eventDescription: TextView = itemView.findViewById(R.id.description_item)

        fun bind(eventEntity: EventEntity, onEventItemClickListener: OnEventItemClickListener){
            try {
                Picasso.get().load(eventEntity.image?.toUri()).into(eventImage)
            }catch (e: SecurityException){

            }
            eventTitle.text = eventEntity.title
            eventDescription.text = eventEntity.description
            itemView.setOnClickListener{
                onEventItemClickListener.onClicked(eventEntity)
            }
        }
    }
}