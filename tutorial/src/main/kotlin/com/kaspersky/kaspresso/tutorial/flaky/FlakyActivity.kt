package com.kaspersky.kaspresso.tutorial.flaky

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.databinding.ActivityFlakyBinding
import kotlinx.coroutines.delay

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
        binding = ActivityFlakyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startLoading()
    }

    private fun startLoading() {
        lifecycleScope.launchWhenResumed {
            delay(LITTLE_TIMEOUT_MILLIS)
            binding.text1.text = getString(R.string.text_1)
            binding.progressBar1.isVisible = false
            delay(MEDIUM_TIMEOUT_MILLIS)
            binding.text2.text = getString(R.string.text_2)
            binding.progressBar2.isVisible = false
            delay(BIG_TIMEOUT_MILLIS)
            binding.text3.text = getString(R.string.text_3)
            binding.progressBar3.isVisible = false
            binding.text4.text = getString(R.string.text_4)
            binding.progressBar4.isVisible = false
            binding.text5.text = getString(R.string.text_5)
            binding.progressBar5.isVisible = false
        }
    }

    companion object {
        private const val LITTLE_TIMEOUT_MILLIS = 1000L
        private const val MEDIUM_TIMEOUT_MILLIS = 3000L
        private const val BIG_TIMEOUT_MILLIS = 10000L
    }
}
