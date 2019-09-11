package com.kaspersky.kaspressample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.flaky.ListStubActivity
import com.kaspersky.kaspressample.flaky.RecyclerStubActivity
import com.kaspersky.kaspressample.flaky.CommonFlakyStubActivity
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

        activity_main_button_recycler_stub.setOnClickListener {
            startActivity(
                Intent(this, RecyclerStubActivity::class.java)
            )
        }

        activity_main_button_list_stub.setOnClickListener {
            startActivity(
                Intent(this, ListStubActivity::class.java)
            )
        }

        activity_main_button_scroll_view_stub.setOnClickListener {
            startActivity(
                Intent(this, CommonFlakyStubActivity::class.java)
            )
        }
    }
}