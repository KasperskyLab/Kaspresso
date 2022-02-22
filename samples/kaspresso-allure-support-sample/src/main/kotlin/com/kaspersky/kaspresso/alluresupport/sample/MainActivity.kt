package com.kaspersky.kaspresso.alluresupport.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.alluresupport.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.increment.setOnClickListener(::incrementAction)
        binding.decrement.setOnClickListener(::decrementAction)
        binding.clear.setOnClickListener(::clearAction)

        if (savedInstanceState == null) {
            updateText(currentValue)
        }

        setContentView(binding.root)
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
        binding.value.text = getString(R.string.value_placeholder, value)
    }
}
