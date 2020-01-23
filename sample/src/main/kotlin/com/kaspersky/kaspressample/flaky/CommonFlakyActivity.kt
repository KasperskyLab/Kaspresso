package com.kaspersky.kaspressample.flaky

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.activity_common_flaky.*

class CommonFlakyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_flaky)

        kautomator_mode.setOnClickListener { startAsync(3_000, 20_000) }
        kakao_mode.setOnClickListener { startAsync(2_000, 12_000) }
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