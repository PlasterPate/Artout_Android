package com.mbglobal.artoutandroid.ui.addevent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventFragment
import kotlinx.android.synthetic.main.fragment_manage_event.*

class AddEventFragment : ManageEventFragment() {

    //image pick code
    private val IMAGE_PICK_CODE = 1000
    //Permission code
    private val PERMISSION_CODE = 1001

    private val addEventViewModel : AddEventViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[AddEventViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image_pick.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        //intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image_pick.setImageURI(data?.data)
        }
    }
}