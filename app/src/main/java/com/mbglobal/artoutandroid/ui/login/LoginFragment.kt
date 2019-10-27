package com.mbglobal.artoutandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {

    val loginViewModel : LoginViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
        initializeObservers()
    }

    private fun initializeListeners() {
        login_button.setOnClickListener {
            loginViewModel.onLoginClicked(phone_number_edit_text.text.toString(), password_edit_text.text.toString())
        }

        register_button.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun initializeObservers() {
        loginViewModel.loginStatus.observe(this, Observer { status ->
            Toast.makeText(requireContext(), if (status) "Success" else "Failure", Toast
                .LENGTH_LONG).show()
        })
    }

}