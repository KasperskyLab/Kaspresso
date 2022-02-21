package com.kaspersky.kaspressample.sharedtest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kaspersky.kaspressample.databinding.ActivitySharedtestBinding
import kotlinx.coroutines.delay

private const val TIMEOUT = 2000L

class SharedTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySharedtestBinding.inflate(layoutInflater)

        lifecycleScope.launchWhenResumed {
            delay(TIMEOUT)
            binding.toggleButton.visibility = View.VISIBLE
        }

        setContentView(binding.root)
    }
}
