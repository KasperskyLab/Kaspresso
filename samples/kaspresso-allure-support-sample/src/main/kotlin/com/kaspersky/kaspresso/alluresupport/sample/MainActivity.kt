package com.kaspersky.kaspresso.alluresupport.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.alluresupport.sample.databinding.ActivityMainBinding

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

    private var currentValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.increment.setOnClickListener(::incrementAction)
        binding.decrement.setOnClickListener(::decrementAction)
        binding.clear.setOnClickListener(::clearAction)

        if (savedInstanceState == null) {
            updateText(currentValue)
        }

        setContentView(binding.root)
    }

    private fun incrementAction(view: View) {
        updateText(++currentValue)
    }

    private fun decrementAction(view: View) {
        updateText(--currentValue)
    }

    private fun clearAction(view: View) {
        currentValue = 0
        updateText(currentValue)
    }

    private fun updateText(value: Int) {
        binding.value.text = getString(R.string.value_placeholder, value)
    }
}
