package com.kaspersky.kaspressample.devicefull

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.databinding.ActivityDeviceFullWindowSampleBinding

class DeviceFullWindowSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDeviceFullWindowSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
