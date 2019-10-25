package com.mbglobal.artoutandroid

import android.os.Bundle
import com.mbglobal.artoutandroid.ui.login.LoginFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContentFrame, LoginFragment())
            .addToBackStack("Login")
            .commit()
    }
}