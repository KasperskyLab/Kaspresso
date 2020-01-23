package com.kaspersky.kaspressample.compose

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.activity_complex_compose.*
import androidx.appcompat.app.AlertDialog


class ComplexComposeSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complex_compose)

        activity_compose_start.setOnClickListener {
            when((0..1).random()) {
                0 -> makeVisibleSlightly(activity_compose_stage_1_1)
                1 -> showAlertDialogSlightly()
            }
        }
        activity_compose_stage_1_1.setOnClickListener {
            makeVisibleSlightly(activity_compose_stage_1_2)
        }
        activity_compose_stage_1_2.setOnClickListener {
            makeVisibleSlightly(activity_compose_finish)
        }
        activity_compose_stage_2_2.setOnClickListener {
            makeVisibleSlightly(activity_compose_finish)
        }
    }

    private fun makeVisibleSlightly(view: View) {
        Handler(mainLooper)
            .apply {
                postDelayed({ view.visibility = View.VISIBLE }, 3_000)
            }
    }

    private fun showAlertDialogSlightly() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle(R.string.compose_screen_dialog_title)
            setMessage(R.string.compose_screen_dialog_message)
            setPositiveButton(R.string.compose_screen_dialog_pos_button) {
                    _, _ -> makeVisibleSlightly(activity_compose_stage_2_2)
            }
        }
        builder.create().show()
    }
}