package com.mbglobal.artoutandroid.ui.manageEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentManageEventBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment

abstract class ManageEventFragment : BaseFragment() {

    lateinit var binding : FragmentManageEventBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_manage_event, container, false
        )
        return binding.root
    }
}