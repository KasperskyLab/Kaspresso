package com.kaspersky.kaspresso.kautomatorsample.autoscroll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.kautomatorsample.R
import com.kaspersky.kaspresso.kautomatorsample.databinding.ActivityAutoScrollBinding

class AutoScrollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_scroll)

        val binding = ActivityAutoScrollBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}
