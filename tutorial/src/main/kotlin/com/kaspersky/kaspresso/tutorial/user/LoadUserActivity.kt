package com.kaspersky.kaspresso.tutorial.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.databinding.ActivityLoadUserBinding

class LoadUserActivity : AppCompatActivity() {

    private val binding: ActivityLoadUserBinding by lazy {
        ActivityLoadUserBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoadUserFragment.newInstance())
                .commit()
        }
    }
}
