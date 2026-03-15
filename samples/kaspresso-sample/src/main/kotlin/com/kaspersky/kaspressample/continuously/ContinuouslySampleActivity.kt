package com.kaspersky.kaspressample.continuously

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.ActivityContinuouslyBinding
import java.util.concurrent.TimeUnit
import kotlin.random.Random

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

class ContinuouslySampleActivity : AppCompatActivity() {

    private companion object {
        private val FAKE_MIN_DELAY = TimeUnit.SECONDS.toMillis(1)
        private val FAKE_MAX_DELAY = TimeUnit.SECONDS.toMillis(5)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityContinuouslyBinding.inflate(layoutInflater)
        binding.continuouslyStartBtn.setOnClickListener {
            binding.continuouslyStartBtn.isEnabled = false

            Handler(mainLooper).apply {
                postDelayed(
                    {
                        // By some reason we can/want to show this dialog only until Android Oreo
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                            AlertDialog.Builder(this@ContinuouslySampleActivity).apply {
                                setTitle(R.string.continuously_dialog_title)
                                setPositiveButton(android.R.string.ok) { _, _ -> }
                                show()
                            }
                        }
                        binding.continuouslyStartBtn.isEnabled = true
                    },
                    // This timeout emulating real background work of application,
                    // like fetching from Internet, or computing some device taken data,
                    // is the thing we can't influence in E2E tests
                    Random.Default.nextLong(FAKE_MIN_DELAY, FAKE_MAX_DELAY)
                )
            }
        }

        setContentView(binding.root)
    }
}
