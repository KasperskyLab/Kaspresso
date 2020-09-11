package com.kaspersky.adbserver.sample

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.adbserver.device.AdbTerminal
import java.util.concurrent.Executors

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
