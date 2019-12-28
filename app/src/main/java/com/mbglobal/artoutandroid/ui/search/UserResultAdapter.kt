package com.mbglobal.artoutandroid.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.data.entity.user.UserEntity

class UserResultAdapter : RecyclerView.Adapter<UserResultItemViewHolder>() {

    var users = mutableListOf<UserEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserResultItemViewHolder {
        val inflate : (Int) -> View = {
            LayoutInflater.from(parent.context).inflate(it, parent, false)
        }

        val view = inflate(R.layout.item_user_result_item)

        return UserResultItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserResultItemViewHolder, position: Int) {
        holder.bind(users[position])
    }
}