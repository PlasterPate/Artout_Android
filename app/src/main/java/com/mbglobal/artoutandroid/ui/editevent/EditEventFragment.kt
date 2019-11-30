package com.mbglobal.artoutandroid.ui.editevent

import android.os.Bundle
import android.view.View
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
import javax.inject.Inject

class EditEventFragment : ManageEventFragment() {

    @Inject
    lateinit var userRepository: UserRepository

    private val editEventViewModel : EditEventViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[EditEventViewModel::class.java]
    }

    private val eventId : Int by lazy {
        EditEventFragmentArgs.fromBundle(arguments!!).eventId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progress.visibility = View.VISIBLE
        binding.layoutGroup.visibility = View.INVISIBLE
        initializeListeners()
        initializeObservers()
        val pageName = getString(R.string.edit_event)
        editEventViewModel.setPageName(pageName)
        editEventViewModel.loadEvent(eventId)
    }

    fun initializeListeners(){
        binding.submitButton.setOnClickListener {
            val eventEntity = AddEventEntity(
                title = binding.titleEditText.text.toString(),
                description = binding.descriptionEditText.text.toString(),
                startDate = binding.startDateEditText.text.toString(),
                endDate = binding.endDateEditText.text.toString(),
                category = binding.categoryEditText.text.toString(),
                eventOwner = userRepository.getUser().blockingGet()!!.toInt(),
                location = LocationEntity(12.0, 10.0),
                image = "https://www.euroarts.com/sites/default/files/styles/product_cover_mobile/public/media_product/Argerich%20%26%20Barenboim%20_c_Arnaldo%20Colombaroli%20%282%29.jpg"
            )
            editEventViewModel.editEvent(eventId, eventEntity)
        }
    }

    fun initializeObservers(){
        editEventViewModel.pageNameText.observe(this, Observer {
            binding.pageName.text = it
        })

        editEventViewModel.addedId.observe(this, Observer { eventId ->
            eventId?.let {
                findNavController().navigate(
                    EditEventFragmentDirections.actionEditEventFragmentToEventDetailsFragment(it)
                )
            }
        })

        editEventViewModel.eventEntity.observe(this, Observer {
            binding.titleEditText.setText(it.title)
            binding.categoryEditText.setText(it.category)
            binding.descriptionEditText.setText(it.description)
            binding.startDateEditText.setText(it.startDate)
            binding.endDateEditText.setText(it.endDate)
            Picasso.get().load(it.image).into(binding.imagePick)
            binding.layoutGroup.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE
        })
    }
}