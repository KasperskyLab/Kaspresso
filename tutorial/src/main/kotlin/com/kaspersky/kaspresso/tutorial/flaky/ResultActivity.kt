package com.kaspersky.kaspresso.tutorial.flaky

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.databinding.ResultActivityBinding

class ResultActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ResultActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
