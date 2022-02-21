package com.kaspersky.kaspresso.tutorial.simple

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.databinding.ActivitySimpleBinding

class SimpleActivity : AppCompatActivity() {

    companion object {
        private const val TIMEOUT: Long = 2000
    }

    private lateinit var binding: ActivitySimpleBinding
    private val handler = Handler(Looper.getMainLooper())

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
            handler.postDelayed({ binding.edit.visibility = View.VISIBLE }, TIMEOUT)
        }
    }
}
