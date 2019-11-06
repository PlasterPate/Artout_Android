package com.mbglobal.artoutandroid.ui.manageEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.base.BaseFragment

abstract class ManageEventFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_manage_event, container, false)
    }
}