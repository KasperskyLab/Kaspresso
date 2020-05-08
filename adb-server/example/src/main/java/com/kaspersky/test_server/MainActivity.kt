package com.kaspersky.test_server

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private val executor = Executors.newCachedThreadPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnExecuteAdb = findViewById<Button>(R.id.btn_execute_adb)
        val editTextAdb = findViewById<EditText>(R.id.et_adb_command)
        btnExecuteAdb.setOnClickListener { onBtnClick(editTextAdb) }
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
                val result = runCatching { AdbTerminal.executeAdb(command) }
                runOnUiThread {
                    Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
