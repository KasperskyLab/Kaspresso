package com.kaspersky.kaspressample.upgrade

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.ActivityUpgradeTestBinding

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

class UpgradeTestActivity : AppCompatActivity() {

    companion object {
        private const val KEY_VALUE = "upgrade_value"
        private const val DEFAULT_VALUE = "Undefined"
    }

    private val appVersion: String
        get() = packageManager.getPackageInfo(packageName, 0).versionName ?: ""

    private lateinit var binding: ActivityUpgradeTestBinding
    private lateinit var prefs: SharedPreferences

    private var currentValue: String?
        get() = prefs.getString(KEY_VALUE, DEFAULT_VALUE)
        set(value) = prefs.edit { putString(KEY_VALUE, value) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpgradeTestBinding.inflate(layoutInflater)

        prefs = getPreferences(Context.MODE_PRIVATE)

        binding.upgradeVersion.text = getString(R.string.upgrade_version_placeholder, appVersion)
        binding.upgradeValueCurrent.text = getString(R.string.upgrade_value_placeholder, currentValue)
        binding.upgradeApplyBtn.setOnClickListener { updateValue() }

        setContentView(binding.root)
    }

    private fun updateValue() {
        val text = binding.upgradeValueInput.text.toString()

        if (text.isNotBlank()) {
            currentValue = text
            binding.upgradeValueCurrent.text = getString(R.string.upgrade_value_placeholder, text)
        }
    }
}
