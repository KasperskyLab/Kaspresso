package com.kaspersky.kaspresso.tutorial.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.R

class LoadUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_user)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoadUserFragment.newInstance())
                .commit()
        }
    }
}
