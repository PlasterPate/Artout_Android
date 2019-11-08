package com.mbglobal.artoutandroid.ui.profile.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.data.entity.event.EventEntity
import com.squareup.picasso.Picasso

class EventAdapter(private val onEventItemClickListener: onEventItemClickListener) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var data = mutableListOf<EventEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_event, parent, false)

        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(data[position], onEventItemClickListener)
    }

    fun addData(it: EventEntity) {
        data.add(it)
        notifyDataSetChanged()
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val eventImage = itemView.findViewById<ImageView>(R.id.image_item)
        val eventTitle = itemView.findViewById<TextView>(R.id.title_item)
        val eventDescription = itemView.findViewById<TextView>(R.id.description_item)

        fun bind(eventEntity: EventEntity, onEventItemClickListener: onEventItemClickListener){
            Picasso.get().load(eventEntity.image).into(eventImage)
            eventTitle.text = eventEntity.title
            eventDescription.text = eventEntity.description
            itemView.setOnClickListener{
                onEventItemClickListener.onClicked(eventEntity)
            }
        }
    }
}