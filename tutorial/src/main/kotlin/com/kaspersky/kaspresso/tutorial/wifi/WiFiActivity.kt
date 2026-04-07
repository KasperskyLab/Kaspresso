package com.kaspersky.kaspresso.tutorial.wifi

import android.app.Application
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.databinding.ActivityWifiBinding

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

class WiFiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWifiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWifiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkWifiBtn.setOnClickListener {
            if (isWiFiEnabled(application)) {
                binding.wifiStatus.setText(R.string.enabled_status)
            } else {
                binding.wifiStatus.setText(R.string.disabled_status)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_WIFI_STATUS_KEY, binding.wifiStatus.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString(EXTRA_WIFI_STATUS_KEY)?.let {
            binding.wifiStatus.text = it
        }
    }

    private fun isWiFiEnabled(context: Application): Boolean =
        (context.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.isWifiEnabled
            ?: throw IllegalStateException("WifiManager is unavailable")

    companion object {
        private const val EXTRA_WIFI_STATUS_KEY = "EXTRA_WIFI_STATUS_KEY"
    }
}
