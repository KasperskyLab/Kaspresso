package com.kaspersky.kaspressample.device_full

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.databinding.ActivityDeviceFullSampleBinding

class DeviceFullSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDeviceFullSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
