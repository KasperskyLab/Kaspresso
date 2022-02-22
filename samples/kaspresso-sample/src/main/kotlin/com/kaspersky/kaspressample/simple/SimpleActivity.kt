package com.kaspersky.kaspressample.simple

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.databinding.ActivitySimpleBinding
import java.util.concurrent.Executors

class SimpleActivity : AppCompatActivity() {

    companion object {
        private const val TIMEOUT: Long = 2000
    }

    private lateinit var binding: ActivitySimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.button1.setOnClickListener {
            binding.button2.visibility = View.VISIBLE
        }
        binding.button2.setOnClickListener {
            // special sleep to emulate ui block operation to check attempt method correctness
            Executors.newSingleThreadExecutor().submit {
                Thread.sleep(TIMEOUT)
                runOnUiThread { binding.edit.visibility = View.VISIBLE }
            }
        }
    }
}
