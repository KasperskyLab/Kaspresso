package com.kaspersky.kaspressample.flaky

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_common_flaky.*

class CommonFlakyActivity : AppCompatActivity() {

    private companion object {
        private val DELAY_FOR_BUTTON = TimeUnit.SECONDS.toMillis(6)
        private val DELAY_FOR_TEXTVIEW = TimeUnit.SECONDS.toMillis(12)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_flaky)

        Handler(mainLooper)
            .apply { postDelayed({ scroll_view_tv6.text = getString(R.string.common_flaky_final_textview) },
                DELAY_FOR_TEXTVIEW
            ) }
            .apply { postDelayed({ scroll_view_btn5.text = getString(R.string.common_flaky_final_button) },
                DELAY_FOR_BUTTON
            ) }
    }
}