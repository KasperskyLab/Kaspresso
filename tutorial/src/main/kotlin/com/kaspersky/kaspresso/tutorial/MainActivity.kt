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
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import com.kaspersky.kaspresso.tutorial.wifi.WiFiActivity

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
        binding.loadUserActivityBtn.setOnClickListener {
            startActivity(Intent(this, LoadUserActivity::class.java))
        }
    }
}
