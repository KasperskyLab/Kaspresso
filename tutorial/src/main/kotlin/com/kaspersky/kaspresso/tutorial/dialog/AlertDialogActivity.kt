package com.kaspersky.kaspresso.tutorial.dialog

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.databinding.ActivityWithAlertDialogBinding

class AlertDialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWithAlertDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showDialogButton.setOnClickListener {
            showDialog()
        }
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
