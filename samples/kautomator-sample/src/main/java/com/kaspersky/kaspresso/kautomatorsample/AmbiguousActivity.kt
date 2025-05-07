package com.kaspersky.kaspresso.kautomatorsample

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class AmbiguousActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambiguous)

        val buttons: List<Button> = listOf(
            findViewById(R.id.ambiguous_button),
            findViewById(R.id.ambiguous_redesigned_button),
            findViewById(R.id.ambiguous_new_button),
            findViewById(R.id.ambiguous_vendor_button),
        )

        buttons[Random.nextInt(buttons.size)].visibility = View.VISIBLE
    }
}
