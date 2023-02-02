package com.kaspersky.kaspressample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.autoscrollfallback.AutoscrollScrollViewWithPaddingActivity
import com.kaspersky.kaspressample.compose.ComplexComposeSampleActivity
import com.kaspersky.kaspressample.continuously.ContinuouslySampleActivity
import com.kaspersky.kaspressample.databinding.ActivityMainBinding
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspressample.devicefull.DeviceFullWindowSampleActivity
import com.kaspersky.kaspressample.flaky.CommonFlakyActivity
import com.kaspersky.kaspressample.idlingwait.WaitForIdleActivity
import com.kaspersky.kaspressample.measure.MeasureActivity
import com.kaspersky.kaspressample.simple.SimpleActivity
import com.kaspersky.kaspressample.systemlanguage.ChangeLocaleActivity
import com.kaspersky.kaspressample.upgrade.UpgradeTestActivity
import com.kaspersky.kaspressample.web.WebViewActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.activityMainAutoScrollScrollViewWithPaddingButton.setOnClickListener {
            startActivity(Intent(this, AutoscrollScrollViewWithPaddingActivity::class.java))
        }

        binding.activityMainSimpleSampleButton.setOnClickListener {
            startActivity(Intent(this, SimpleActivity::class.java))
        }

        binding.activityMainWebviewSampleButton.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        binding.activityMainFlakySampleButton.setOnClickListener {
            startActivity(Intent(this, CommonFlakyActivity::class.java))
        }

        binding.activityMainContinuouslySampleButton.setOnClickListener {
            startActivity(Intent(this, ContinuouslySampleActivity::class.java))
        }

        binding.activityMainUpgradeScenarioButton.setOnClickListener {
            startActivity(Intent(this, UpgradeTestActivity::class.java))
        }

        binding.activityMainComplexComposeSampleButton.setOnClickListener {
            startActivity(Intent(this, ComplexComposeSampleActivity::class.java))
        }

        binding.activityMainIdlewaitingSampleButton.setOnClickListener {
            startActivity(Intent(this, WaitForIdleActivity::class.java))
        }

        binding.activityMainMeasureSampleButton.setOnClickListener {
            startActivity(Intent(this, MeasureActivity::class.java))
        }

        binding.activityMainDeviceButton.setOnClickListener {
            startActivity(Intent(this, DeviceSampleActivity::class.java))
        }

        binding.activityMainDeviceFullButton.setOnClickListener {
            startActivity(Intent(this, DeviceFullWindowSampleActivity::class.java))
        }

        binding.activityMainChangeLocaleMidTestButton.setOnClickListener {
            startActivity(Intent(this, ChangeLocaleActivity::class.java))
        }

        setContentView(binding.root)
    }
}
