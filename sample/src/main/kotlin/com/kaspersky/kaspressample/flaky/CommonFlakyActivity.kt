package com.kaspersky.kaspressample.flaky

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.activity_common_flaky.*

class CommonFlakyActivity : AppCompatActivity() {

    companion object {
        private const val FIRST_DELAY = 2_000L
        private const val SECOND_DELAY = 15_000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_flaky)
    }

    override fun onResume() {
        super.onResume()
        startAsync(FIRST_DELAY, SECOND_DELAY)
    }

    private fun startAsync(firstDelayMs: Long, secondDelayMs: Long) {
        Handler(mainLooper)
            .apply { postDelayed({ scroll_view_btn5.text = getString(R.string.common_flaky_final_button) },
                firstDelayMs
            ) }
            .apply { postDelayed({ scroll_view_tv6.text = getString(R.string.common_flaky_final_textview) },
                secondDelayMs
            ) }
    }
}