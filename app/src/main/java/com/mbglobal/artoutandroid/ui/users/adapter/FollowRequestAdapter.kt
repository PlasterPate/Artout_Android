package com.mbglobal.artoutandroid.ui.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.users.adapter.listener.OnFollowRequestClickListener
import com.mbglobal.data.entity.user.UserEntity

class FollowRequestAdapter(private val onFollowRequestClickListener: OnFollowRequestClickListener): RecyclerView.Adapter<FollowRequestViewHolder>() {

    var data:MutableList<UserEntity> = object : ArrayList<UserEntity>() {

        init {
            notifyDataSetChanged()
        }

        override fun add(element: UserEntity): Boolean {
            val result = super.add(element)
            notifyDataSetChanged()
            return result
        }

        override fun set(index: Int, element: UserEntity): UserEntity {
            val result = super.set(index, element)
            notifyItemChanged(index)
            return result
        }

        override fun remove(element: UserEntity): Boolean {
            val idx = this.indexOf(element)
            super.remove(element)
            if (idx != -1) {
                notifyDataSetChanged()
                return true
            } else {
                return false
            }
        }
    }
    set (value) {
        field.clear()
        field.addAll(value)
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