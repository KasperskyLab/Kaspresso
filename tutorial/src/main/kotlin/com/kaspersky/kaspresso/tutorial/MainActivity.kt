package com.kaspersky.kaspresso.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaspersky.kaspresso.tutorial.simple.SimpleActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_simple_sample_button.setOnClickListener {
            startActivity(
                Intent(this, SimpleActivity::class.java)
            )
        }
    }
}
