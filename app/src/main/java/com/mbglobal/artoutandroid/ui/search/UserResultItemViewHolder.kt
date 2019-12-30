package com.mbglobal.artoutandroid.ui.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.users.adapter.OnUserItemClickListener
import com.mbglobal.data.entity.user.UserEntity
import com.squareup.picasso.Picasso

class UserResultItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView by lazy {
        itemView.findViewById(R.id.tv_name) as TextView
    }

    private val userImage: ImageView by lazy {
        itemView.findViewById(R.id.iv_user_image) as ImageView
    }

    fun bind(
        userEntity: UserEntity,
        onUserItemClickListener: OnUserItemClickListener
    ) {
        name.text = userEntity.firstName + " " + userEntity.lastName
        Picasso.get().load("https://pbs.twimg.com/profile_images/959929674355765248/fk3ALoeH.jpg").into(userImage)
        itemView.setOnClickListener {
            onUserItemClickListener.onClicked(userEntity)
        }
    }
}
