package com.mbglobal.artoutandroid.ui.manageevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentManageEventBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val c = Calendar.getInstance()
        val myear = c.get(Calendar.YEAR)
        val mmonth = c.get(Calendar.MONTH)
        val mday = c.get(Calendar.DAY_OF_MONTH)
        binding.startDateEditText.setOnClickListener{
            val datePicker = DatePickerDialog.newInstance(
                DatePickerDialog.OnDateSetListener{view,  year,  monthOfYear,  dayOfMonth ->
                    binding.startDateEditText
                    .setText(String.format("%04d-%02d-%02d", year, (monthOfYear + 1), dayOfMonth))
            },myear, mmonth, mday)
            datePicker.setCancelColor(resources.getColor(R.color.white))
            datePicker.show(fragmentManager!!, "DatePicker")
        }
    }
}