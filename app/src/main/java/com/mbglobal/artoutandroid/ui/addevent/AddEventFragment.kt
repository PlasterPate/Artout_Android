package com.mbglobal.artoutandroid.ui.addevent

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventFragment
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventViewModel
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.LocationEntity
import com.mbglobal.data.repository.UserRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_manage_event.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddEventFragment : ManageEventFragment() {

    @Inject
    lateinit var userRepository: UserRepository



    private val addEventViewModel: AddEventViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[AddEventViewModel::class.java]
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeListeners()
        initializeObservers()

        val pageName = getString(R.string.add_event)
        addEventViewModel.setPageName(pageName)
    }

    private fun initializeObservers() {
        addEventViewModel.pageNameText.observe(this, Observer {
            binding.pageName.text = it
        })

        addEventViewModel.eventImage.observe(this, Observer { imageUri ->
            Picasso.get().load(imageUri?.toUri()).into(binding.imagePick)
        })

        addEventViewModel.addedId.observe(this, Observer { id ->
            id?.let {
                findNavController().navigate(
                    AddEventFragmentDirections
                        .actionAddEventFragmentToEventDetailsFragment(it)
                )
            }
        })
    }

    private fun initializeListeners() {
        image_pick.setOnClickListener {
            checkPermissions()
            pickImageFromGallery()
        }

        binding.submitButton.setOnClickListener {
            val eventEntity = AddEventEntity(
                title = binding.titleEditText.text.toString(),
                description = binding.descriptionEditText.text.toString(),
                startDate = binding.startDateEditText.text.toString(),
                startTime = binding.startTimeEditText.text.toString(),
                endDate = binding.endDateEditText.text.toString(),
                endTime = binding.endTimeEditText.text.toString(),
                category = binding.categoryEditText.text.toString(),
                owner = 0,
                location = LocationEntity(12.0, 10.0),
                image = "https://vignette.wikia.nocookie.net/friends/images/9/94/Central_Perk.jpg"
            )
            addEventViewModel.addEvent(eventEntity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            addEventViewModel.setImage(data?.data!!)
        }
    }

//    @SuppressLint("Recycle")
//    private fun getPath(uri: Uri): String{
//        val projection = Array<String>(1){MediaStore.Images.Media.DATA}
//        val cursor = requireContext().contentResolver.query(uri, projection, null,null,null)
//        val colIdx = cursor?.getColumnIndex(MediaStore.Images.Media.DATA)
//        var s = ""
//        cursor?.let {
//            it.moveToFirst()
//            s = it.getString(colIdx!!)
//            it.close()
//        }
//        return s
//    }
}