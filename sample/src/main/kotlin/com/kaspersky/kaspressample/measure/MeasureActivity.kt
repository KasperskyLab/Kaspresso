package com.kaspersky.kaspressample.measure

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.activity_measure.*
import kotlinx.android.synthetic.main.activity_simple.*
import kotlinx.android.synthetic.main.activity_simple.button_2

class MeasureActivity : AppCompatActivity() {

    private var button2ClicksCount = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure)
        textview.text = "${getString(R.string.measure_fragment_text_textview)}$button2ClicksCount"
        button_2.setOnClickListener {
            button2ClicksCount++
            textview.text = "${getString(R.string.measure_fragment_text_textview)}$button2ClicksCount"
        }
    }
}