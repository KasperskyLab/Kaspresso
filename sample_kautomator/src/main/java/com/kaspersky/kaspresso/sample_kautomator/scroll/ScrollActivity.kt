package com.kaspersky.kaspresso.sample_kautomator.scroll

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.sample_kautomator.R
import com.kaspersky.kaspresso.sample_kautomator.model.SimpleModel
import kotlinx.android.synthetic.main.activity_scroll.*

class ScrollActivity : AppCompatActivity() {

    companion object {
        private const val ITEM_COUNT = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)

        (0..ITEM_COUNT)
            .map { SimpleModel.randomizeNewItem() }
            .toMutableList()
            .apply { SimpleModel.richWithLabels(this) }
            .map { model ->
                val view = layoutInflater.inflate(R.layout.item_recycler, layout, false)
                view.findViewById<TextView>(R.id.title).text = model.text
                view.findViewById<TextView>(R.id.subtitle).text = model.number.toString()
                view
            }
            .forEach {
                layout.addView(it)
            }
    }
}