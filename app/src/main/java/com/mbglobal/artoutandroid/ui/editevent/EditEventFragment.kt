package com.mbglobal.artoutandroid.ui.manageevent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders

class EditEventFragment : ManageEventFragment() {

    private val editEventViewModel : EditEventViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[EditEventViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}