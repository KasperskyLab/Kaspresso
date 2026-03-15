package com.kaspersky.kaspressample.compose

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.ActivityComplexComposeBinding

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

class ComplexComposeSampleActivity : AppCompatActivity() {

    companion object {
        private const val DELAY = 2_000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityComplexComposeBinding.inflate(layoutInflater)
        binding.activityComposeStart.setOnClickListener {
            when ((0..1).random()) {
                0 -> makeVisibleSlightly(binding.activityComposeStage1)
                1 -> showAlertDialogSlightly(R.string.compose_screen_dialog_title_1) {
                    showAlertDialogSlightly(R.string.compose_screen_dialog_title_2) {
                        makeVisibleSlightly(binding.activityComposeFinish)
                    }
                }
            }
        }
        binding.activityComposeStage1.setOnClickListener {
            makeVisibleSlightly(binding.activityComposeStage2)
        }
        binding.activityComposeStage2.setOnClickListener {
            makeVisibleSlightly(binding.activityComposeFinish)
        }

        setContentView(binding.root)
    }

    private fun makeVisibleSlightly(view: View) {
        Handler(mainLooper).postDelayed({ view.visibility = View.VISIBLE }, DELAY)
    }

    private fun showAlertDialogSlightly(title: Int, action: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle(title)
            setMessage(R.string.compose_screen_dialog_message)
            setPositiveButton(R.string.compose_screen_dialog_pos_button) { _, _ -> action.invoke() }
        }
        Handler(mainLooper).postDelayed({ builder.create().show() }, DELAY)
    }
}
