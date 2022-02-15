package com.kaspersky.kaspresso.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaspersky.kaspresso.tutorial.databinding.ActivityMainBinding
import com.kaspersky.kaspresso.tutorial.simple.SimpleActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.activityMainSimpleSampleButton.setOnClickListener {
            startActivity(Intent(this, SimpleActivity::class.java))
        }

        setContentView(binding.root)
    }
}
