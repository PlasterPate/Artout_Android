package com.mbglobal.artoutandroid.ui.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.base.BaseFragment

class RegisterFragment : BaseFragment() {

    val registerViewModel : RegisterViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerViewModel.registerStatus.observe(this, Observer { status ->
            if (status) {
                findNavController().navigate(R.id.action_registerFragment_to_timelineFragment)
            } else {
                showRegisterError()
            }
        })

        registerViewModel.registerValidateError.observe(this, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        })
    }

    private fun showRegisterError() {
        Toast.makeText(requireContext(), "Register failed", Toast.LENGTH_LONG).show()
    }

}
