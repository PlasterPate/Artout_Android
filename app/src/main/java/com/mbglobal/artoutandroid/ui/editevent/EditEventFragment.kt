package com.mbglobal.artoutandroid.ui.editevent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventFragment

class EditEventFragment : ManageEventFragment() {

    private val editEventViewModel : EditEventViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[EditEventViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}