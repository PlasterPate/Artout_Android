package com.mbglobal.artoutandroid.ui.addevent

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
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
import javax.inject.Inject

class AddEventFragment : ManageEventFragment() {

    @Inject
    lateinit var userRepository: UserRepository

    //image pick code
    private val IMAGE_PICK_CODE = 1000
    //Permission code
    private val PERMISSION_CODE = 1001

    private val addEventViewModel : AddEventViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[AddEventViewModel::class.java]
    }

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
                findNavController().navigate(AddEventFragmentDirections
                    .actionAddEventFragmentToEventDetailsFragment(it))
            }
        })
    }

    fun initializeListeners(){
        image_pick.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                }
                else{
                    //permission already granted
                    pickImageFromGallery()
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
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
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            addEventViewModel.setImage(data?.data)
        }
    }
}