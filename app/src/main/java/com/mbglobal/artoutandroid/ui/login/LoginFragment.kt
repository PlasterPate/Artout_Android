package com.mbglobal.artoutandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.databinding.FragmentLoginBinding
import com.mbglobal.artoutandroid.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(), LifecycleOwner {

    val loginViewModel : LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
    }

    lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListeners()
        initializeObservers()
    }

    private fun initializeListeners() {
        binding.loginButton.setOnClickListener {
            loginViewModel.onLoginClicked(username_edit_text.text.toString(), password_edit_text.text.toString())
        }

        binding.registerButton.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun initializeObservers() {
        loginViewModel.loginStatus.observe(this, Observer { status ->
            if (status) {
                findNavController().navigate(R.id.action_loginFragment_to_timelineFragment)
            }else{
                Toast.makeText(requireContext(), "login failed", Toast.LENGTH_SHORT).show()
            }
        })
    }



}