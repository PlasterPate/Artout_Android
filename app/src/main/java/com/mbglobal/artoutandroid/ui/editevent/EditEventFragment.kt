package com.mbglobal.artoutandroid.ui.editevent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mbglobal.artoutandroid.ui.manageevent.ManageEventFragment
import com.mbglobal.data.entity.event.AddEventEntity
import com.mbglobal.data.entity.event.LocationEntity
import com.mbglobal.data.repository.UserRepository
import javax.inject.Inject

class EditEventFragment : ManageEventFragment() {

    @Inject
    lateinit var userRepository: UserRepository

    private var eventId : Int? = null

    private val editEventViewModel : EditEventViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[EditEventViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeListeners()
        initializeObservers()

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
            eventId = EditEventFragmentArgs.fromBundle(arguments!!).eventId
            editEventViewModel.editEvent(eventId!!, eventEntity)
        }
    }

    fun initializeObservers(){
        editEventViewModel.addedId.observe(this, Observer { eventId ->
            eventId?.let {
                findNavController().navigate(
                    EditEventFragmentDirections.actionEditEventFragmentToEventDetailsFragment(eventId)
                )
            }
        })
    }
}