package com.mbglobal.artoutandroid.ui.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.data.entity.event.EventEntity
import com.squareup.picasso.Picasso

class EventAdapter(private val onEventItemClickListener: OnEventItemClickListener) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var data = listOf<EventEntity>()

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_event, parent, false)

        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(data[position], onEventItemClickListener)
    }

    fun refreshData(events: List<EventEntity>) {
        data = events
        notifyDataSetChanged()
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val eventImage = itemView.findViewById<ImageView>(R.id.image_item)
        val eventTitle = itemView.findViewById<TextView>(R.id.title_item)
        val eventDescription = itemView.findViewById<TextView>(R.id.description_item)

        fun bind(eventEntity: EventEntity, onEventItemClickListener: OnEventItemClickListener){
            Picasso.get().load(eventEntity.image?.toUri()).into(eventImage)
            eventTitle.text = eventEntity.title
            eventDescription.text = eventEntity.description
            itemView.setOnClickListener{
                onEventItemClickListener.onClicked(eventEntity)
            }
        }
    }
}