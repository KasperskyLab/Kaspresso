package com.kaspersky.kaspressample.sharedtest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.activity_sharedtest.*
import kotlinx.coroutines.delay

class SharedTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharedtest)
        lifecycleScope.launchWhenResumed {
            delay(2000)
            toggleButton.visibility = View.VISIBLE
        }
    }
}

