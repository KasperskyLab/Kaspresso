package com.kaspersky.kaspressample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_scroll_view_stub.scroll_view_stub_btn5

class ScrollViewStubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view_stub)

        Handler(mainLooper).postDelayed({ scroll_view_stub_btn5.text = "bzzz" }, TimeUnit.SECONDS.toMillis(2))
    }
}