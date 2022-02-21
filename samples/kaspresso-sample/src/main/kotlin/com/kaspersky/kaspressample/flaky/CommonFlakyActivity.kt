package com.kaspersky.kaspressample.flaky

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.ActivityCommonFlakyBinding

class CommonFlakyActivity : AppCompatActivity() {

    companion object {
        private const val FIRST_DELAY = 2_000L
        private const val SECOND_DELAY = 15_000L
    }

    private lateinit var binding: ActivityCommonFlakyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonFlakyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        startAsync(FIRST_DELAY, SECOND_DELAY)
    }

    @Suppress("SameParameterValue")
    private fun startAsync(firstDelayMs: Long, secondDelayMs: Long) {
        Handler(mainLooper).apply {
            postDelayed(
                { binding.scrollViewBtn5.text = getString(R.string.common_flaky_final_button) },
                firstDelayMs
            )
        }.apply {
            postDelayed(
                { binding.scrollViewTv6.text = getString(R.string.common_flaky_final_textview) },
                secondDelayMs
            )
        }
    }
}
