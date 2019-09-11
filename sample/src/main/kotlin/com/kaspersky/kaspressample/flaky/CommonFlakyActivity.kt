package com.kaspersky.kaspressample.flaky

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_scroll_view_stub.*

class CommonFlakyActivity : AppCompatActivity() {

    private companion object {
        private val DELAY_FOR_BUTTON = TimeUnit.SECONDS.toMillis(4)
        private val DELAY_FOR_TEXTVIEW = TimeUnit.SECONDS.toMillis(8)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view_stub)

        Handler(mainLooper)
            .apply { postDelayed({ scroll_view_stub_tv6.text = getString(R.string.common_flaky_final_textview) },
                DELAY_FOR_TEXTVIEW
            ) }
            .apply { postDelayed({ scroll_view_stub_btn5.text = getString(R.string.common_flaky_final_button) },
                DELAY_FOR_BUTTON
            ) }
    }
}