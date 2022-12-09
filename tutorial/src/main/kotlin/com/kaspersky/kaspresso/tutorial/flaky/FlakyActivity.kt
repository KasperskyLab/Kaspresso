package com.kaspersky.kaspresso.tutorial.flaky

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.databinding.ActivityFlakyBinding
import kotlinx.coroutines.delay

class FlakyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlakyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlakyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startAnimation()
    }

    private fun startAnimation() {
        lifecycleScope.launchWhenResumed {
            delay(MEDIUM_TIMEOUT_MILLIS)
            binding.rootScrollView.smoothScrollBy(START_OF_SCREEN_X, END_OF_SCREEN_Y)
            delay(MEDIUM_TIMEOUT_MILLIS)
            binding.button5.text = getString(R.string.button_5_changed)
            delay(BIG_TIMEOUT_MILLIS)
            binding.rootScrollView.smoothScrollTo(START_OF_SCREEN_X, START_OF_SCREEN_Y)
            delay(LITTLE_TIMEOUT_MILLIS)
            binding.button1.text = getString(R.string.button_1_changed)
        }
    }

    companion object {
        private const val START_OF_SCREEN_X = 0
        private const val START_OF_SCREEN_Y = 0
        private const val END_OF_SCREEN_Y = 10000

        private const val LITTLE_TIMEOUT_MILLIS = 1000L
        private const val MEDIUM_TIMEOUT_MILLIS = 3000L
        private const val BIG_TIMEOUT_MILLIS = 10000L
    }
}
