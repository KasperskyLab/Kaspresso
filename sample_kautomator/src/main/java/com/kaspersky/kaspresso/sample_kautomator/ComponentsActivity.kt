package com.kaspersky.kaspresso.sample_kautomator

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ui_components.*

class ComponentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_components)

        showDialogBtn.setOnClickListener { showDialog() }
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