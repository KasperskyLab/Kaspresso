package com.kaspersky.kaspresso.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.databinding.ActivityMainBinding
import com.kaspersky.kaspresso.tutorial.flaky.FlakyActivity
import com.kaspersky.kaspresso.tutorial.lists.NoteListActivity
import com.kaspersky.kaspresso.tutorial.login.LoginActivity
import com.kaspersky.kaspresso.tutorial.notification.NotificationActivity
import com.kaspersky.kaspresso.tutorial.permission.MakeCallActivity
import com.kaspersky.kaspresso.tutorial.simple.SimpleActivity
import com.kaspersky.kaspresso.tutorial.wifi.WiFiActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        binding.simpleActivityBtn.setOnClickListener {
            startActivity(Intent(this, SimpleActivity::class.java))
        }
        binding.wifiActivityBtn.setOnClickListener {
            startActivity(Intent(this, WiFiActivity::class.java))
        }
        binding.notificationActivityBtn.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
        binding.loginActivityBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.makeCallActivityBtn.setOnClickListener {
            startActivity(Intent(this, MakeCallActivity::class.java))
        }
        binding.flakyActivityBtn.setOnClickListener {
            startActivity(Intent(this, FlakyActivity::class.java))
        }
        binding.listActivityBtn.setOnClickListener {
            startActivity(Intent(this, NoteListActivity::class.java))
        }
    }
}
