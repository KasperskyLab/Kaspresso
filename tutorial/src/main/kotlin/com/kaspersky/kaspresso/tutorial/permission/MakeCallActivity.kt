package com.kaspersky.kaspresso.tutorial.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.databinding.ActivityMakeCallBinding

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

class MakeCallActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakeCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.makeCallBtn.setOnClickListener {
            if (!isPermissionGranted()) {
                requestPermission()
            } else {
                phoneCall()
            }
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(CALL_PHONE_PERMISSION),
                CALL_PHONE_PERMISSION_RC
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (
            requestCode == CALL_PHONE_PERMISSION_RC &&
            permissions[0] == android.Manifest.permission.CALL_PHONE &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            phoneCall()
        }
    }

    private fun phoneCall() {
        val number = binding.inputNumber.text.toString().trim()
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
    }

    private fun isPermissionGranted(): Boolean = packageManager.checkPermission(
        android.Manifest.permission.CALL_PHONE,
        packageName
    ) == PackageManager.PERMISSION_GRANTED

    companion object {

        private const val CALL_PHONE_PERMISSION = android.Manifest.permission.CALL_PHONE
        private const val CALL_PHONE_PERMISSION_RC = 100
    }
}
