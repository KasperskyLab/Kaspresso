package com.kaspersky.kaspresso.kautomatorsample.scroll

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.kautomatorsample.R
import com.kaspersky.kaspresso.kautomatorsample.databinding.ActivityScrollBinding
import com.kaspersky.kaspresso.kautomatorsample.model.SimpleModel

class ScrollActivity : AppCompatActivity() {

    companion object {
        private const val ITEM_COUNT = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)

        val binding = ActivityScrollBinding.inflate(layoutInflater)

        (0..ITEM_COUNT)
            .map { SimpleModel.randomizeNewItem() }
            .toMutableList()
            .apply { SimpleModel.richWithLabels(this) }
            .map { model ->
                val view = layoutInflater.inflate(R.layout.item_recycler, binding.layout, false)
                view.findViewById<TextView>(R.id.title).text = model.text
                view.findViewById<TextView>(R.id.subtitle).text = model.number.toString()
                view
            }
            .forEach {
                binding.layout.addView(it)
            }

        setContentView(binding.root)
    }
}
