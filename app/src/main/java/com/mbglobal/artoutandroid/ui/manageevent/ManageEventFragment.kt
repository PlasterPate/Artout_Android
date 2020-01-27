package com.mbglobal.artoutandroid.ui.manageevent

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentManageEventBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import com.mbglobal.data.repository.UserRepository
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*
import javax.inject.Inject

abstract class ManageEventFragment : BaseFragment() {

    //image pick code
    protected val IMAGE_PICK_CODE = 1000
    //Permission code
    protected val PERMISSION_CODE = 1001

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
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.btnCancel.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.startDateEditText.let {editText ->
            editText.setOnClickListener{
                createDatePicker(editText)
            }
        }
        binding.endDateEditText.let {editText ->
            editText.setOnClickListener{
                createDatePicker(editText)
            }
        }
        binding.startTimeEditText.let {editText ->
            editText.setOnClickListener{
                createTimePicker(editText)
            }
        }
        binding.endTimeEditText.let {editText ->
            editText.setOnClickListener{
                createTimePicker(editText)
            }
        }
    }

    fun createDatePicker(editText : TextInputEditText){
        val c = Calendar.getInstance()
        val myear = c.get(Calendar.YEAR)
        val mmonth = c.get(Calendar.MONTH)
        val mday = c.get(Calendar.DAY_OF_MONTH)
        val datePicker = DatePickerDialog.newInstance(
            DatePickerDialog.OnDateSetListener{view,  year,  monthOfYear,  dayOfMonth ->
                editText.setText(String.format("%04d-%02d-%02d", year, (monthOfYear + 1), dayOfMonth))
            },myear, mmonth, mday)
        datePicker.setCancelColor(resources.getColor(R.color.white))
        datePicker.setOkColor(resources.getColor(R.color.white))
        datePicker.show(fragmentManager!!, "DatePicker")
    }

    fun createTimePicker(editText : TextInputEditText){
        val timePicker = TimePickerDialog.newInstance(
            TimePickerDialog.OnTimeSetListener{view,  hourOfDay,  minute,  second ->
                editText.setText(String.format("%02d:%02d", hourOfDay, minute))
            },0,0,true)
        timePicker.setCancelColor(resources.getColor(R.color.white))
        timePicker.setOkColor(resources.getColor(R.color.white))
        timePicker.show(fragmentManager!!, "TimePicker")
    }

    protected fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICK_CODE)
    }



    protected fun checkPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_DENIED
        ) {
            //permission denied
            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            //show popup to request runtime permission
            requestPermissions(permissions, PERMISSION_CODE)
        }
    }
}