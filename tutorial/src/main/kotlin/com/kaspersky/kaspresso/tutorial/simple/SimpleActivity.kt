package com.kaspersky.kaspresso.tutorial.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.databinding.ActivitySimpleBinding

class SimpleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.changeTitleBtn.setOnClickListener {
            binding.simpleTitle.text = binding.inputText.text
        }
    }
}
