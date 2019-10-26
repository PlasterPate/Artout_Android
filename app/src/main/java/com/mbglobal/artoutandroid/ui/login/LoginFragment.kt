package com.mbglobal.artoutandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        btnLogin.setOnClickListener {
            loginViewModel.onLoginClicked(tvUserName.text.toString(), tvPassword.text.toString())
        }
    }

    private fun initializeObservers() {
        loginViewModel.loginStatus.observe(this, Observer { status ->
            Toast.makeText(requireContext(), if (status) "Success" else "Failure", Toast
                .LENGTH_LONG).show()
        })
    }

}