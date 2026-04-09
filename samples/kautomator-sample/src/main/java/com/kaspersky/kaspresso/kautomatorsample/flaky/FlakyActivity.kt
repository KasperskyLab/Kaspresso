package com.kaspersky.kaspresso.kautomatorsample.flaky

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.kautomatorsample.R
import com.kaspersky.kaspresso.kautomatorsample.databinding.ActivityFlakyBinding

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

class FlakyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlakyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flaky)

        binding = ActivityFlakyBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        startAsync(FIRST_DELAY)
    }

    @Suppress("SameParameterValue")
    private fun startAsync(firstDelayMs: Long) {
        Handler(mainLooper).apply {
            postDelayed(
                { binding.flakyButton1.text = getString(R.string.menu_item_2) },
                firstDelayMs
            )
        }
    }

    companion object {
        private const val FIRST_DELAY = 3_500L
    }
}
