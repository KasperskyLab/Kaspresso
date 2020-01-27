package com.kaspersky.kaspressample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.compose.ComplexComposeSampleActivity
import com.kaspersky.kaspressample.continuously.ContinuouslySampleActivity
import com.kaspersky.kaspressample.flaky.CommonFlakyActivity
import com.kaspersky.kaspressample.simple.SimpleActivity
import com.kaspersky.kaspressample.upgrade.UpgradeTestActivity
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

        activity_main_button_continuously_sample.setOnClickListener {
            startActivity(
                Intent(this, ContinuouslySampleActivity::class.java)
            )
        }

        activity_main_button_upgrade_scenario.setOnClickListener {
            startActivity(
                Intent(this, UpgradeTestActivity::class.java)
            )
        }

        activity_main_button_complex_compose_sample.setOnClickListener {
            startActivity(
                Intent(this, ComplexComposeSampleActivity::class.java)
            )
        }
    }
}