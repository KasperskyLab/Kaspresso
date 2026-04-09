package com.kaspersky.kaspressample.idlingwait

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.ActivityIdlewaitingBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

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

class WaitForIdleActivity : AppCompatActivity() {

    companion object {
        private const val INIT_DELAY: Long = 2_000
        private const val PERIOD: Long = 100
        private const val TOTAL_TIME: Long = 20_000
    }

    private lateinit var binding: ActivityIdlewaitingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdlewaitingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val scheduler = Executors.newScheduledThreadPool(1)
        val periodicTask = scheduler.scheduleAtFixedRate(
            { Handler(Looper.getMainLooper()).post { binding.edit.setText(R.string.idlewaiting_fragment_text_edittext) } },
            INIT_DELAY,
            PERIOD,
            TimeUnit.MILLISECONDS
        )
        scheduler.schedule({ periodicTask.cancel(true) }, TOTAL_TIME, TimeUnit.MILLISECONDS)
    }
}
