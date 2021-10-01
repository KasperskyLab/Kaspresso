package com.kaspersky.kaspresso.alluresupport.sample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button
    private lateinit var clearButton: Button
    private lateinit var valueText: TextView

    private var currentValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        incrementButton = findViewById(R.id.increment)
        decrementButton = findViewById(R.id.decrement)
        clearButton = findViewById(R.id.clear)
        valueText = findViewById(R.id.value)

        incrementButton.setOnClickListener(::incrementAction)
        decrementButton.setOnClickListener(::decrementAction)
        clearButton.setOnClickListener(::clearAction)

        if (savedInstanceState == null) {
            updateText(currentValue)
        }
    }

    private fun incrementAction(view: View) {
        updateText(++currentValue)
    }

    private fun decrementAction(view: View) {
        updateText(--currentValue)
    }

    private fun clearAction(view: View) {
        currentValue = 0
        updateText(currentValue)
    }

    private fun updateText(value: Int) {
        valueText.text = getString(R.string.value_placeholder, value)
    }
}
