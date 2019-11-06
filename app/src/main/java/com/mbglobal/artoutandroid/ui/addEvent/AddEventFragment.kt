package com.mbglobal.artoutandroid.ui.manageEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mbglobal.artoutandroid.R

class AddEventFragment : ManageEventFragment() {

    private val addEventViewModel : AddEventViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[AddEventViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}