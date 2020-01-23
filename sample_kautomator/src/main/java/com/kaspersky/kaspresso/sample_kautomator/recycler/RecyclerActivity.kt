package com.kaspersky.kaspresso.sample_kautomator.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaspersky.kaspresso.sample_kautomator.R
import com.kaspersky.kaspresso.sample_kautomator.model.SimpleModel
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    companion object {
        private const val ITEM_COUNT = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recycler.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RecyclerActivity)
            adapter = RecyclerAdapter(generateItems())
        }
    }

    private fun generateItems(): List<SimpleModel> {
        return (0..ITEM_COUNT).map { SimpleModel.randomizeNewItem() }.toMutableList().apply {
            SimpleModel.richWithLabels(this)
        }
    }
}