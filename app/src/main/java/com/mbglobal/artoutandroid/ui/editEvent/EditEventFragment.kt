package com.mbglobal.artoutandroid.ui.manageEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.base.BaseFragment

class EditEventFragment : ManageEventFragment() {

    private val editEventViewModel : EditEventViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[EditEventViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}