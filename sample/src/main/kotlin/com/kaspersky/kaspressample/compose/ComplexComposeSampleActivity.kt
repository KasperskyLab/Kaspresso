package com.kaspersky.kaspressample.compose

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.activity_complex_compose.*

class ComplexComposeSampleActivity : AppCompatActivity() {

    companion object {
        private const val DELAY = 2_000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complex_compose)

        activity_compose_start.setOnClickListener {
            when ((0..1).random()) {
                0 -> makeVisibleSlightly(activity_compose_stage_1)
                1 -> showAlertDialogSlightly(R.string.compose_screen_dialog_title_1) {
                    showAlertDialogSlightly(R.string.compose_screen_dialog_title_2) {
                        makeVisibleSlightly(activity_compose_finish)
                    }
                }
            }
        }
        activity_compose_stage_1.setOnClickListener {
            makeVisibleSlightly(activity_compose_stage_2)
        }
        activity_compose_stage_2.setOnClickListener {
            makeVisibleSlightly(activity_compose_finish)
        }
    }

    private fun makeVisibleSlightly(view: View) {
        Handler(mainLooper).postDelayed({ view.visibility = View.VISIBLE }, DELAY)
    }

    private fun showAlertDialogSlightly(title: Int, action: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle(title)
            setMessage(R.string.compose_screen_dialog_message)
            setPositiveButton(R.string.compose_screen_dialog_pos_button) { _, _ -> action.invoke() }
        }
        Handler(mainLooper).postDelayed({ builder.create().show() }, DELAY)
    }
}