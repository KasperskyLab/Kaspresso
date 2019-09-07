package com.kaspersky.kaspressample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_scroll_view_stub.*

class ScrollViewStubActivity : AppCompatActivity() {

    private companion object {
        private val DELAY_FOR_BUTTON = TimeUnit.SECONDS.toMillis(5)
        private val DELAY_FOR_TEXTVIEW = TimeUnit.SECONDS.toMillis(2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view_stub)

        Handler(mainLooper)
            .apply { postDelayed({ scroll_view_stub_tv6.text = "bzzz" }, DELAY_FOR_TEXTVIEW) }
            .apply { postDelayed({ scroll_view_stub_btn5.text = "bzzz" }, DELAY_FOR_BUTTON) }
    }
}