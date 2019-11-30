package com.mbglobal.artoutandroid.ui.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnFollowRequestClickListener
import com.mbglobal.data.entity.user.UserEntity

class FollowRequestAdapter(private val onFollowRequestClickListener: OnFollowRequestClickListener): RecyclerView.Adapter<FollowRequestViewHolder>() {

    var data = listOf<UserEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowRequestViewHolder {

        val inflate : (Int) -> View = {
            LayoutInflater.from(parent.context).inflate(it, parent, false)
        }
        return FollowRequestViewHolder(inflate(R.layout.item_user_request))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FollowRequestViewHolder, position: Int) {
        holder.bind(data[position], onFollowRequestClickListener)
    }
}