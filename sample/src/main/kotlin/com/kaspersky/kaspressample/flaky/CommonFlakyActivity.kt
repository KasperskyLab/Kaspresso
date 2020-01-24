package com.kaspersky.kaspressample.flaky

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.activity_common_flaky.*

class CommonFlakyActivity : AppCompatActivity() {

    companion object {
        private const val FIRST_KAKAO_DELAY = 2_000L
        private const val FIRST_KAUTOMATOR_DELAY = 3_000L
        private const val SECOND_KAKAO_DELAY = 12_000L
        private const val SECOND_KAUTOMATOR_DELAY = 20_000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_flaky)

        kautomator_mode.setOnClickListener { startAsync(FIRST_KAUTOMATOR_DELAY, SECOND_KAUTOMATOR_DELAY) }
        kakao_mode.setOnClickListener { startAsync(FIRST_KAKAO_DELAY, SECOND_KAKAO_DELAY) }
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