package com.kaspersky.kaspressample.flaky

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.ActivityCommonFlakyBinding

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

class CommonFlakyActivity : AppCompatActivity() {

    companion object {
        private const val FIRST_DELAY = 2_000L
        private const val SECOND_DELAY = 15_000L
    }

    private lateinit var binding: ActivityCommonFlakyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonFlakyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        startAsync(FIRST_DELAY, SECOND_DELAY)
    }

    @Suppress("SameParameterValue")
    private fun startAsync(firstDelayMs: Long, secondDelayMs: Long) {
        Handler(mainLooper).apply {
            postDelayed(
                { binding.scrollViewBtn5.text = getString(R.string.common_flaky_final_button) },
                firstDelayMs
            )
        }.apply {
            postDelayed(
                { binding.scrollViewTv6.text = getString(R.string.common_flaky_final_textview) },
                secondDelayMs
            )
        }
    }
}
