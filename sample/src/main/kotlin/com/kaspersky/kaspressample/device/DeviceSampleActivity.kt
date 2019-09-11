package com.kaspersky.kaspressample.device

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.activity_device_sample.request_permission_button

class DeviceSampleActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 7
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_sample)

        request_permission_button.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_CALL_LOG),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }
}