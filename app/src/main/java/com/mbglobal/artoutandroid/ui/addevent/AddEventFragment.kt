package com.mbglobal.artoutandroid.ui.addevent

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

    //image pick code
    private val IMAGE_PICK_CODE = 1000
    //Permission code
    private val PERMISSION_CODE = 1001

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
                endDate = binding.endDateEditText.text.toString(),
                category = binding.categoryEditText.text.toString(),
                eventOwner = userRepository.getUser().blockingGet()!!.toInt(),
                location = LocationEntity(12.0, 10.0),
                image = addEventViewModel.eventImage.value
            )
            addEventViewModel.addEvent(eventEntity)
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            addEventViewModel.setImage(data?.data)
        }
    }

    private fun checkPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED
        ) {
            //permission denied
            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            //show popup to request runtime permission
            requestPermissions(permissions, PERMISSION_CODE)
        }
    }
}