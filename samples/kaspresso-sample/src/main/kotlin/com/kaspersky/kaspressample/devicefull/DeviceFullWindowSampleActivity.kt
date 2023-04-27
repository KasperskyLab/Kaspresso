package com.kaspersky.kaspressample.devicefull

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.databinding.ActivityDeviceFullWindowSampleBinding

class DeviceFullWindowSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDeviceFullWindowSampleBinding.inflate(layoutInflater)
        binding.showDialog.setOnClickListener {
            showDialog()
        }

        setContentView(binding.root)
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("Title")
            .setMessage("Message")
            .setPositiveButton("Positive") { dialog, _ -> dialog.dismiss() }
            .setNegativeButton("Negative") { dialog, _ -> dialog.dismiss() }
            .setNeutralButton("Neutral") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
