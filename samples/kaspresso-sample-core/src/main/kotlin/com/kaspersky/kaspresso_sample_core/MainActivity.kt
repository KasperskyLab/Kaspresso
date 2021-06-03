package com.kaspersky.kaspresso_sample_core

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso_sample_core.compose.ComplexComposeSampleActivity
import com.kaspersky.kaspresso_sample_core.continuously.ContinuouslySampleActivity
import com.kaspersky.kaspresso_sample_core.flaky.CommonFlakyActivity
import com.kaspersky.kaspresso_sample_core.idlingwait.WaitForIdleActivity
import com.kaspersky.kaspresso_sample_core.measure.MeasureActivity
import com.kaspersky.kaspresso_sample_core.simple.SimpleActivity
import com.kaspersky.kaspresso_sample_core.upgrade.UpgradeTestActivity
import com.kaspersky.kaspresso_sample_core.web.WebViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_simple_sample_button.setOnClickListener {
            startActivity(
                Intent(this, SimpleActivity::class.java)
            )
        }

        activity_main_webview_sample_button.setOnClickListener {
            startActivity(
                Intent(this, WebViewActivity::class.java)
            )
        }

        activity_main_flaky_sample_button.setOnClickListener {
            startActivity(
                Intent(this, CommonFlakyActivity::class.java)
            )
        }

        activity_main_continuously_sample_button.setOnClickListener {
            startActivity(
                Intent(this, ContinuouslySampleActivity::class.java)
            )
        }

        activity_main_upgrade_scenario_button.setOnClickListener {
            startActivity(
                Intent(this, UpgradeTestActivity::class.java)
            )
        }

        activity_main_complex_compose_sample_button.setOnClickListener {
            startActivity(
                Intent(this, ComplexComposeSampleActivity::class.java)
            )
        }

        activity_main_idlewaiting_sample_button.setOnClickListener {
            startActivity(
                Intent(this, WaitForIdleActivity::class.java)
            )
        }

        activity_main_measure_sample_button.setOnClickListener {
            startActivity(
                Intent(this, MeasureActivity::class.java)
            )
        }
    }
}
