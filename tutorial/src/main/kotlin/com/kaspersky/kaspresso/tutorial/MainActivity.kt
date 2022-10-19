package com.kaspersky.kaspresso.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.databinding.ActivityMainBinding
import com.kaspersky.kaspresso.tutorial.flaky.FlakyActivity
import com.kaspersky.kaspresso.tutorial.simple.SimpleActivity
import com.kaspersky.kaspresso.tutorial.wifi.WiFiActivity

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.simpleActivityBtn.setOnClickListener {
            startActivity(Intent(this, SimpleActivity::class.java))
        }
        binding.flakyActivityBtn.setOnClickListener {
            startActivity(Intent(this, FlakyActivity::class.java))
        }
        binding.internetAvailability.setOnClickListener {
            startActivity(Intent(this, WiFiActivity::class.java))
        }
    }
}
