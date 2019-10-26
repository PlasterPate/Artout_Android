package com.mbglobal.artoutandroid.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mbglobal.artoutandroid.R
import com.mbglobal.artoutandroid.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContentFrame, LoginFragment())
            .commit()
    }
}