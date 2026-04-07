package com.kaspersky.adbserver.sample

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.adbserver.device.AdbTerminal
import java.util.concurrent.Executors

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

    private val executor = Executors.newCachedThreadPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnExecuteAdb = findViewById<Button>(R.id.btn_execute_adb)
        val editTextAdb = findViewById<EditText>(R.id.et_adb_command)
        val btnConnectAdb = findViewById<Button>(R.id.btn_connect_adb)
        val btnDisconnectAdb = findViewById<Button>(R.id.btn_disconnect_adb)

        btnExecuteAdb.setOnClickListener { onBtnClick(editTextAdb) }
        btnConnectAdb.setOnClickListener { AdbTerminal.connect() }
        btnDisconnectAdb.setOnClickListener { AdbTerminal.disconnect() }
    }

    override fun onResume() {
        super.onResume()
        AdbTerminal.connect()
    }

    override fun onPause() {
        AdbTerminal.disconnect()
        super.onPause()
    }

    private fun onBtnClick(editTextAdb: EditText) {
        val command = editTextAdb.text.toString()
        if (command.isNotEmpty()) {
            executor.execute {
                val result = runCatching {
                    AdbTerminal.executeAdb(
                        command
                    )
                }
                runOnUiThread {
                    Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
