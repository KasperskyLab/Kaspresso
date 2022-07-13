package com.kaspersky.kaspresso.tutorial.flaky

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.databinding.FlakyActivityBinding
import java.util.concurrent.TimeUnit

class FlakyActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FlakyActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sixthElementBtn.setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }

        Handler(mainLooper)
            .apply {
                postDelayed(
                    { binding.secondElementTv.visibility = View.VISIBLE },
                    VISIBILITY_DELAY
                )
            }
            .apply {
                postDelayed(
                    { binding.secondElementTv.text = "new second element text" },
                    VISIBILITY_DELAY + TEXT_DELAY
                )
            }
            .apply {
                postDelayed(
                    { binding.sixthElementBtn.visibility = View.VISIBLE },
                    VISIBILITY_DELAY * 2 + TEXT_DELAY
                )
            }
            .apply {
                postDelayed(
                    { binding.sixthElementBtn.text = "new sixth element text" },
                    VISIBILITY_DELAY * 2 + TEXT_DELAY * 2
                )
            }
    }

    companion object {
        private val VISIBILITY_DELAY = TimeUnit.SECONDS.toMillis(1)
        private val TEXT_DELAY = TimeUnit.SECONDS.toMillis(3)
    }
}
