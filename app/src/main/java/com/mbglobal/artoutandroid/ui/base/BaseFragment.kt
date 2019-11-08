package com.mbglobal.artoutandroid.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import android.view.KeyboardShortcutGroup
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(), LifecycleOwner {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.hideKeyboard()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}