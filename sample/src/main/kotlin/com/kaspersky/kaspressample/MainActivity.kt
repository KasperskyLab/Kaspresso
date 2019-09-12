package com.kaspersky.kaspressample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kaspersky.kaspressample.flaky.CommonFlakyActivity
import com.kaspersky.kaspressample.simple.SimpleActivity
import com.kaspersky.kaspressample.web.WebViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_button_simple.setOnClickListener {
            startActivity(
                Intent(this, SimpleActivity::class.java)
            )
        }

        activity_main_button_webview.setOnClickListener {
            startActivity(
                Intent(this, WebViewActivity::class.java)
            )
        }

        activity_main_button_scroll_view_sample.setOnClickListener {
            startActivity(
                Intent(this, CommonFlakyActivity::class.java)
            )
        }
    }
}