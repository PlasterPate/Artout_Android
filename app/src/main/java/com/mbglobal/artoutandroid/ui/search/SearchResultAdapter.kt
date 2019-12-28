package com.mbglobal.artoutandroid.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.eventlist.adapter.OnEventItemClickListener
import com.mbglobal.data.entity.event.EventEntity
import com.mbglobal.data.entity.user.UserEntity
import com.squareup.picasso.Picasso

class SearchResultAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var events = mutableListOf<EventEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var users = mutableListOf<UserEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflate : (Int) -> View = {
            LayoutInflater.from(parent.context).inflate(it, parent, false)
        }

        return when (viewType) {
            ITEM_EVENT -> {
                EventItemViewHolder(inflate(R.layout.list_item_event))
            }
            ITEM_USERS_LIST -> {
                UserListViewHolder(inflate(R.layout.item_user_list))
            }
            else -> {
                throw Exception("Illegal item")
            }
        }
    }

    override fun getItemCount(): Int {
        return events.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_USERS_LIST -> {
                (holder as UserListViewHolder).bind(users)
            }
            ITEM_EVENT -> {
                (holder as EventItemViewHolder).bind(events[position - 1])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_USERS_LIST
            else -> ITEM_EVENT
        }
    }

    companion object {
        const val ITEM_USERS_LIST = 100
        const val ITEM_EVENT = 200
    }

    class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val users: RecyclerView by lazy {
            itemView.findViewById(R.id.rv_users) as RecyclerView
        }

        fun bind(userList: List<UserEntity>) {
            users.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            users.adapter = UserResultAdapter().apply {
                users = userList.toMutableList()
            }
        }
    }

    class EventItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventImage: ImageView = itemView.findViewById(R.id.image_item)
        private val eventTitle: TextView = itemView.findViewById(R.id.title_item)
        private val eventDescription: TextView = itemView.findViewById(R.id.description_item)

        fun bind(eventEntity: EventEntity){
            try {
                Picasso.get().load(eventEntity.image?.toUri()).into(eventImage)
            }catch (e: SecurityException){

            }
            eventTitle.text = eventEntity.title
            eventDescription.text = eventEntity.description
        }
    }
}
